package com.example.utmguide.model

import android.os.Parcel
import android.os.Parcelable

data class MailResponse(val value: List<Mail>)

data class Mail(
    val subject: String,
    val isRead: Boolean,
    val receivedDateTime: String,
    val sender: Sender?,
    val body: Body?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!,
        parcel.readParcelable<Sender>(Sender::class.java.classLoader),
        parcel.readParcelable<Body>(Sender::class.java.classLoader)

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subject)
        parcel.writeByte(if (isRead) 1 else 0)
        parcel.writeString(receivedDateTime)
        parcel.writeParcelable(sender, Parcelable.PARCELABLE_WRITE_RETURN_VALUE)
        parcel.writeParcelable(body, Parcelable.PARCELABLE_WRITE_RETURN_VALUE)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Mail> {
        override fun createFromParcel(parcel: Parcel): Mail {
            return Mail(parcel)
        }

        override fun newArray(size: Int): Array<Mail?> {
            return arrayOfNulls(size)
        }
    }
}

data class Sender(
    val emailAddress: EmailAddress?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable< EmailAddress>(EmailAddress::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Sender> {
        override fun createFromParcel(parcel: Parcel): Sender {
            return Sender(parcel)
        }

        override fun newArray(size: Int): Array<Sender?> {
            return arrayOfNulls(size)
        }
    }
}

data class EmailAddress(
    val name: String,
    val address: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EmailAddress> {
        override fun createFromParcel(parcel: Parcel): EmailAddress {
            return EmailAddress(parcel)
        }

        override fun newArray(size: Int): Array<EmailAddress?> {
            return arrayOfNulls(size)
        }
    }
}

data class Body(
    val contentType: String,
    val content: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(contentType)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Body> {
        override fun createFromParcel(parcel: Parcel): Body {
            return Body(parcel)
        }

        override fun newArray(size: Int): Array<Body?> {
            return arrayOfNulls(size)
        }
    }
}
