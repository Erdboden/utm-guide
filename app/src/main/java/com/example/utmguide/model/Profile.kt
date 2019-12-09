package com.example.utmguide.model

data class Profile(
    val displayName: String,
    val givenName: String,
    val mail: String,
    val surname: String,
    val userPrincipalName: String,
    val id: String
)