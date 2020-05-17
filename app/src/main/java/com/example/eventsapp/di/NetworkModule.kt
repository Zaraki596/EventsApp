package com.example.eventsapp.di

import android.content.Context
import com.example.eventsapp.data.api.EventsApiService
import com.example.eventsapp.data.repo.EventsDataRepository
import com.example.eventsapp.utils.isNetworkAvailable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
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
            .client(getOkHttpClient(androidContext()))
            .build()
            .create(EventsApiService::class.java)
    }

    single {
        EventsDataRepository(get())
    }
}

fun getOkHttpClient(context: Context): OkHttpClient {
    val cacheSize = (5 * 1024 * 1024).toLong()
    val myCache = Cache(context.cacheDir, cacheSize)

    return OkHttpClient.Builder()
        .cache(myCache)
        .addInterceptor { chain ->
            var request = chain.request()
            request = if (isNetworkAvailable(context))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                ).build()
            chain.proceed(request)
        }
        .build()
}