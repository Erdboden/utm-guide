package com.example.utmguide.model

import android.os.Parcel
import android.os.Parcelable

data class EventsResponse(val value: List<Event>)

data class Event(
    val subject: String,
    val bodyPreview: String,
    val body: Body?,
    val start: Time,
    val end: Time,
    val location: Location,
    val organizer: Organizer,
    val attendees: List<Attendees>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable<Body>(Body::class.java.classLoader)!!,
        parcel.readParcelable<Time>(Time::class.java.classLoader)!!,
        parcel.readParcelable<Time>(Time::class.java.classLoader)!!,
        parcel.readParcelable<Location>(Location::class.java.classLoader)!!,
        parcel.readParcelable<Organizer>(Organizer::class.java.classLoader)!!,
        parcel.createTypedArrayList(Attendees)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subject)
        parcel.writeString(bodyPreview)
        parcel.writeParcelable(body, flags)
        parcel.writeTypedList(attendees)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}


data class Attendees(val status: Status, val emailAddress: EmailAddress):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Status::class.java.classLoader)!!,
        parcel.readParcelable(EmailAddress::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(status, flags)
        parcel.writeParcelable(emailAddress, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attendees> {
        override fun createFromParcel(parcel: Parcel): Attendees {
            return Attendees(parcel)
        }

        override fun newArray(size: Int): Array<Attendees?> {
            return arrayOfNulls(size)
        }
    }

}

data class Status(val response: String, val time: String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(response)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Status> {
        override fun createFromParcel(parcel: Parcel): Status {
            return Status(parcel)
        }

        override fun newArray(size: Int): Array<Status?> {
            return arrayOfNulls(size)
        }
    }
}

data class Time(
    val dateTime: String?,
    val timeZone: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateTime)
        parcel.writeString(timeZone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Time> {
        override fun createFromParcel(parcel: Parcel): Time {
            return Time(parcel)
        }

        override fun newArray(size: Int): Array<Time?> {
            return arrayOfNulls(size)
        }
    }
}

data class Location(
    val displayName: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(displayName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}

data class Organizer(
    val emailAddress: EmailAddress?
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable<EmailAddress>(EmailAddress::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(emailAddress, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Organizer> {
        override fun createFromParcel(parcel: Parcel): Organizer {
            return Organizer(parcel)
        }

        override fun newArray(size: Int): Array<Organizer?> {
            return arrayOfNulls(size)
        }
    }
}

enum class EventStatus(val status: String){
    NONE("none"),
    ORGANIZER("organizer"),
    TENTATIVELY_ACCEPTED("tentativelyAccepted"),
    ACCEPTED("accepted"),
    DECLINED("declined"),
    NOT_RESPONDED("notResponded")
}