package com.example.eventsapp.di

import com.example.eventsapp.data.api.EventsApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@ExperimentalCoroutinesApi
val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(EventsApiService.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .build()
            .create(EventsApiService::class.java)
    }
}