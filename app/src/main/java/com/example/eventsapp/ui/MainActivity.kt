package com.example.eventsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.eventsapp.R
import com.example.eventsapp.data.api.EventsApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val retrofit =   Retrofit.Builder()
            .baseUrl(EventsApiService.BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
            .create(EventsApiService::class.java)



        runBlocking {
           val reponse = retrofit.getEvents().body()
            Log.d("TAG_CHECK", reponse.toString())
        }

    }


}
