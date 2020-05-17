package com.example.eventsapp.data.repo

import com.example.eventsapp.data.api.EventsApiService
import com.example.eventsapp.data.model.EventResponse
import com.example.eventsapp.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

class EventsDataRepository(private val apiService: EventsApiService) {

    @ExperimentalCoroutinesApi
    fun getEventsData(): Flow<State<EventResponse>> {
        return object : NetworkBoundRepository<EventResponse>() {
            override suspend fun fetchFromRemote(): Response<EventResponse> = apiService.getEvents()
        }.asFlow().flowOn(Dispatchers.IO)
    }
}