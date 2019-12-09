package com.example.utmguide.api

import com.example.utmguide.model.EventsResponse
import com.example.utmguide.model.MailResponse
import com.example.utmguide.model.Profile
import retrofit2.http.GET
import retrofit2.http.Header


interface GraphApi {
    @GET("v1.0/me")
    suspend fun me(@Header("Authorization") token: String?): Profile

    @GET("beta/me/messages")
    suspend fun myMail(@Header("Authorization") token: String?): MailResponse

    @GET("beta/me/events?\$select=subject,body,bodyPreview,organizer,attendees,start,end,location")
    suspend fun myEvents(@Header("Authorization") token: String?): EventsResponse
}