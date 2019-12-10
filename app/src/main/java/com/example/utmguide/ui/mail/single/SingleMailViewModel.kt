package com.example.utmguide.ui.mail.single

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utmguide.api.ApiClient
import com.example.utmguide.api.GraphApi
import com.example.utmguide.model.*
import com.example.utmguide.util.Constants
import com.example.utmguide.util.Constants.Companion.EMAIL
import com.microsoft.identity.common.adal.internal.AuthenticationConstants
import com.microsoft.identity.common.adal.internal.AuthenticationConstants.OAuth2
import com.microsoft.identity.common.adal.internal.AuthenticationConstants.OAuth2.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SingleMailViewModel(val context: Context) : ViewModel() {

    private var api: GraphApi? = null;

    val replySent = MutableLiveData<Boolean>().apply { value = false }


    init {
        api = ApiClient.getClient().buildGraphApi()
    }

    fun reply(id: String, sender: Sender, text: String) {
        val reply = ReplyRequest(Message(listOf(sender)), text)

        val token = context.getSharedPreferences(
            Constants.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(ACCESS_TOKEN, "")
        GlobalScope.launch {
            api?.reply(token, id, reply)
            replySent.postValue(true)
        }
    }


    /* Factory for creating FeatureViewModel instances */
    class Factory(val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SingleMailViewModel(context) as T
        }
    }
}