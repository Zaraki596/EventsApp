package com.example.eventsapp.data.model

import android.media.MediaRouter
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Event(

    @Json(name = "_id")
    val id: String = "",
    val min_show_start_time: Int = 0,
    val name: String = "",
    val type: String = "",
    val slug: String = "",
    val horizontal_cover_image: String = "",
    val vertical_cover_image: String = "",
    val city: String = "",
    val venue_id: String = "",
    val venue_name: String = "",
    val venue_date_string: String = "",
    val category_id: CategoryObject,
    /*
    applicable filters contains the data with the value key which have strings value like
    today, tomorrow, weekend
    if it contains all 3 values then event has been passed
    if it contains either 2 i.e :
    today, weekend then its today,
    tomorrow, weekend then its day after today
    if it contains none value then the event never happened or yet to happen
    * */
    val applicable_filters: List<String>?,
    val price_display_string: String = "",
    val min_price: Int = 0

) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CategoryObject(
    @Json(name = "_id")
    val id: String = "",
    val name: String = "",
    val icon_img: String = ""
) : Parcelable
