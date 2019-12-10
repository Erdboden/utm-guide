package com.example.utmguide.api

import com.example.utmguide.model.*
import retrofit2.http.*
import retrofit2.http.Body


interface GraphApi {
    @GET("v1.0/me")
    suspend fun me(@Header("Authorization") token: String?): Profile

    @GET("beta/me/messages")
    suspend fun myMail(@Header("Authorization") token: String?): MailResponse

    @PATCH("beta/me/messages/{id}")
    suspend fun updateMail(@Header("Authorization") token: String?, @Path("id") id: String, @Body mailRequest: MailRequest)

    @POST("beta/me/messages/{id}/reply")
    suspend fun reply(@Header("Authorization") token: String?, @Path("id") id: String, @Body mailReplyRequest: ReplyRequest)


    @GET("beta/me/events?\$select=subject,body,bodyPreview,organizer,attendees,start,end,location")
    suspend fun myEvents(@Header("Authorization") token: String?): EventsResponse
}