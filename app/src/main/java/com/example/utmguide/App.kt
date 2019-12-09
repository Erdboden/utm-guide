package com.example.utmguide

import android.app.Application
import android.content.Context
import com.microsoft.identity.client.IPublicClientApplication
import com.microsoft.identity.client.ISingleAccountPublicClientApplication
import com.microsoft.identity.client.PublicClientApplication
import com.microsoft.identity.client.exception.MsalException

object App {

    private var auth: ISingleAccountPublicClientApplication? = null



    fun createAuth(context: Context): ISingleAccountPublicClientApplication {
        if (auth == null) {
            PublicClientApplication.createSingleAccountPublicClientApplication(
                context,
                R.raw.auth_config,
                object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                    override fun onCreated(application: ISingleAccountPublicClientApplication) {
                        /**
                         * This test app assumes that the app is only going to support one account.
                         * This requires "account_mode" : "SINGLE" in the config json file.
                         *
                         */
                        auth = application
                    }

                    override fun onError(exception: MsalException) {
                        println(exception)
                    }
                })
        }
        return auth!!
    }
}