package com.example.utmguide.ui.mail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utmguide.api.ApiClient
import com.example.utmguide.api.GraphApi
import com.example.utmguide.model.Mail
import com.example.utmguide.model.MailRequest
import com.example.utmguide.model.MailResponse
import com.example.utmguide.util.Constants
import com.example.utmguide.util.Constants.Companion.EMAIL
import com.microsoft.identity.common.adal.internal.AuthenticationConstants
import com.microsoft.identity.common.adal.internal.AuthenticationConstants.OAuth2
import com.microsoft.identity.common.adal.internal.AuthenticationConstants.OAuth2.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MailViewModel(val context: Context) : ViewModel() {

    private var api: GraphApi? = null;

    init {
        api = ApiClient.getClient().buildGraphApi()
    }

    private val _mail = MutableLiveData<List<Mail>>().apply {
        GlobalScope.launch {
            withContext(Main) { value = getMail() }
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text
    val mail: LiveData<List<Mail>> = _mail

    private suspend fun getMail(): List<Mail>? {
        val token = context.getSharedPreferences(
            Constants.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(ACCESS_TOKEN, "")

        val myEmail = context.getSharedPreferences(
            Constants.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(EMAIL, "")
        return api?.myMail(token)?.value
            ?.filter { it.sender?.emailAddress?.address != myEmail }
    }

    fun isRead(id: String, isRead: Boolean) {
        val token = context.getSharedPreferences(
            Constants.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(ACCESS_TOKEN, "")
        GlobalScope.launch {
            api?.updateMail(token, id, MailRequest(true))
            _mail.postValue(getMail())
        }
    }

    /* Factory for creating FeatureViewModel instances */
    class Factory(val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MailViewModel(context) as T
        }
    }
}