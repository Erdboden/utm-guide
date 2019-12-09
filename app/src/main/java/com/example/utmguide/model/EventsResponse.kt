package com.example.utmguide.model

data class EventsResponse(val value: List<Event>)

data class Event(
    val subject: String,
    val bodyPreview: String,
    val body: Body,
    val start: Time,
    val end: Time,
    val location: Location,
    val organizer: EmailAddress,
    val attendees: List<EmailAddress>

)

data class Time(
    val dateTime: String,
    val timeZone: String
)

data class Location(
    val displayName: String
)