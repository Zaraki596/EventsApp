package com.example.eventsapp.di

import com.example.eventsapp.ui.EventsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { EventsViewModel(get()) }
}