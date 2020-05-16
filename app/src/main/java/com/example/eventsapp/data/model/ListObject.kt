package com.example.eventsapp.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize
import kotlin.collections.List

@Parcelize
@JsonClass(generateAdapter = true)
data class ListObject(
    val masterList: Map<String, Event>,
    val categorywiseList: CategoryWiseItem
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class CategoryWiseItem(
    @Json(name = "Health and Fitness")
    val Health_Fitness: List<String>,
    val Games: List<String>,
    @Json(name = "Arts and Theatre")
    val Arts_Theatre: List<String>,
    @Json(name = "Online Course")
    val Online_Course: List<String>,
    val Kids: List<String>,
    val Talks: List<String>,
    val Comedy: List<String>,
    val Music: List<String>,
    val Camping: List<String>
) : Parcelable
