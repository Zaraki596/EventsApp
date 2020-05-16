package com.example.eventsapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class DateObject(
    val today: String = "",
    val today_date_string: String = "",
    val tomorrow: String = "",
    val tomorrow_date_string: String = "",
    val weekend: String = "",
    val weekend_date_string: String = "",
    val next_weekend: String = "",
    val next_weekend_date_string: String = ""
) : Parcelable
