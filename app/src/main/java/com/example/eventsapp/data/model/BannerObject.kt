package com.example.eventsapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class BannerObject(
    @Json(name = "_id")
    val id: String = "",
    val name: String = "",
    val type: String = "",
    val vertical_cover_image: String = "",
    val priority : Int = 0
) : Parcelable