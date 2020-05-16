package com.example.eventsapp.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlin.collections.List

@Parcelize
@JsonClass(generateAdapter = true)
data class EventResponse(
    val list: ListObject,
    val popular: List<Event>,
    val featured: List<Event>,
    val dates: DateObject,
    val banners: List<BannerObject>
) : Parcelable
