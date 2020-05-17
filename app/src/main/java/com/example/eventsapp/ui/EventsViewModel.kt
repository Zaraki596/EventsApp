package com.example.eventsapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventsapp.data.model.EventResponse
import com.example.eventsapp.data.repo.EventsDataRepository
import com.example.eventsapp.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EventsViewModel(private val repository: EventsDataRepository) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<State<EventResponse>>()
    val eventsLiveData: LiveData<State<EventResponse>> = _eventsLiveData

    @ExperimentalCoroutinesApi
    fun getEventsResponse() {
        viewModelScope.launch {
            repository.getEventsData().collect {
                _eventsLiveData.value = it
            }
        }
    }

}
