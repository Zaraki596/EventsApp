package com.example.eventsapp.data.api

import com.example.eventsapp.data.model.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface EventsApiService {
    @GET("home?norm=1&filterBy=go-out&city=mumbai")
    suspend fun getEvents(): Response<EventResponse>

    companion object {
        const val BASE_URL = "https://api.insider.in/"
    }
}