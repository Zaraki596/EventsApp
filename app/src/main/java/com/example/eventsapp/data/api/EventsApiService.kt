package com.example.eventsapp.data.api

import com.example.eventsapp.data.model.EventResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EventsApiService {
    @GET("home?")
    suspend fun getEvents(
        @Query("norm") norm: Int = 1,
        @Query("filterBy") filterBy: String = "go-out",
        @Query("city") city: String = "mumbai"
    ): Response<EventResponse>

    companion object {
        const val BASE_URL = "https://api.insider.in/"
    }
}