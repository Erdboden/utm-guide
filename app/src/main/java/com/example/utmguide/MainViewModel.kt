package com.example.utmguide

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utmguide.api.ApiClient
import com.example.utmguide.api.GraphApi
import com.example.utmguide.model.Mail
import com.example.utmguide.model.MailResponse
import com.example.utmguide.model.Profile
import com.example.utmguide.util.Constants
import com.example.utmguide.util.Constants.Companion.EMAIL
import com.microsoft.identity.common.adal.internal.AuthenticationConstants
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val context: Context) : ViewModel() {

    private var api: GraphApi? = null;

    init {
        api = ApiClient.getClient().buildGraphApi()

        GlobalScope.launch {
            withContext(Main) {
                context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)
                    .edit()
                    .putString(EMAIL, getMe().mail)
                    .apply()
            }
        }
    }

    private suspend fun getMe(): Profile {
        val token = context.getSharedPreferences(
            Constants.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(AuthenticationConstants.OAuth2.ACCESS_TOKEN, "")

        return api?.me(token)!!
    }

    /* Factory for creating FeatureViewModel instances */
    class Factory(val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(context) as T
        }
    }
}