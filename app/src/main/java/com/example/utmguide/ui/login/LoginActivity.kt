package com.example.utmguide.ui.login

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Bundle
import android.util.Base64
import androidx.appcompat.app.AppCompatActivity
import com.example.utmguide.MainActivity
import com.example.utmguide.R
import com.example.utmguide.util.Constants.Companion.SHARED_PREF
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import com.microsoft.identity.common.adal.internal.AuthenticationConstants.OAuth2.ACCESS_TOKEN
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.security.MessageDigest


class LoginActivity : AppCompatActivity() {

    private var auth: ISingleAccountPublicClientApplication? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        PublicClientApplication.createSingleAccountPublicClientApplication(
            this,
            R.raw.auth_config,
            object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
                override fun onCreated(application: ISingleAccountPublicClientApplication) {
                    /**
                     * This test app assumes that the app is only going to support one account.
                     * This requires "account_mode" : "SINGLE" in the config json file.
                     *
                     */
                    auth = application
                    auth!!.signOut(object : ISingleAccountPublicClientApplication.SignOutCallback {
                        override fun onSignOut() {
                        }

                        override fun onError(exception: MsalException) {
                        }
                    })
                    sign_in_btn.setOnClickListener { login() }
                }

                override fun onError(exception: MsalException) {
                    println(exception)
                }
            })

    }



    private fun login() {
        GlobalScope.launch {
            auth!!.signIn(this@LoginActivity,"", arrayOf("mail.read", "mail.readwrite", "calendars.read", "calendars.readwrite"), getAuthInteractiveCallback())
        }
    }

    private fun getAuthInteractiveCallback(): AuthenticationCallback {
        return object : AuthenticationCallback {

            override fun onSuccess(authenticationResult: IAuthenticationResult?) {
                val accessToken = authenticationResult?.accessToken
                getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)
                    .edit()
                    .putString(ACCESS_TOKEN, "Bearer $accessToken")
                    .apply()
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }

            override fun onCancel() {
                println("canceled")
            }

            override fun onError(exception: MsalException?) {
                println(exception)
            }
        }
    }

}

