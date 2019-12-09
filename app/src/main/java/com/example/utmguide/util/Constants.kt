package com.example.utmguide.util

class Constants {
    companion object {
        /* Azure AD Constants */ /* Authority is in the form of https://login.microsoftonline.com/yourtenant.onmicrosoft.com */
        const val AUTHORITY = "https://login.microsoftonline.com/common"
        /* The clientID of your application is a unique identifier which can be obtained from the app registration portal */
         const val CLIENT_ID = "a3afc7db-f137-4c94-a70d-6f2ea97f37de"
        /* Resource URI of the endpoint which will be accessed */
        const val RESOURCE_ID = "https://graph.microsoft.com/"
        /* The Redirect URI of the application (Optional) */
        const val REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob"
        /* Microsoft Graph Constants */
        const val MSGRAPH_URL = "https://graph.microsoft.com/v1.0/me"

        const val SHARED_PREF = "shared_pref"
        const val EMAIL = "email"
    }
}