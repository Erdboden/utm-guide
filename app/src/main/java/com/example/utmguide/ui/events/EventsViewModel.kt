package com.example.utmguide.ui.events

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.utmguide.api.ApiClient
import com.example.utmguide.api.GraphApi
import com.example.utmguide.model.EventsResponse
import com.example.utmguide.model.MailResponse
import com.example.utmguide.util.Constants
import com.microsoft.identity.common.adal.internal.AuthenticationConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsViewModel(val context: Context) : ViewModel() {

    private var api: GraphApi? = null;

    init {
        api = ApiClient.getClient().buildGraphApi()
    }

    private val _events = MutableLiveData<EventsResponse>().apply {
        GlobalScope.launch {
            withContext(Dispatchers.Main) { value = getEvents() }
        }
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
    val events: LiveData<EventsResponse> = _events

    private suspend fun getEvents(): EventsResponse? {
        val token = context.getSharedPreferences(
            Constants.SHARED_PREF,
            Context.MODE_PRIVATE
        ).getString(AuthenticationConstants.OAuth2.ACCESS_TOKEN, "")
        return api?.myEvents(token)
    }

    class Factory(val context: Context) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EventsViewModel(context) as T
        }
    }
}