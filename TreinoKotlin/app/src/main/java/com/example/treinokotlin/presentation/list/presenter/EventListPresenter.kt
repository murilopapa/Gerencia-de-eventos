package com.example.treinokotlin.presentation.list.presenter

import androidx.lifecycle.MutableLiveData
import com.example.treinokotlin.Event
import com.example.treinokotlin.domain.usecases.EventsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EventListPresenter(private val eventsUseCase: EventsUseCase) {
    var eventsLiveData = MutableLiveData<List<Event>>()

    fun getEvents() {
        CoroutineScope(IO).launch {
            eventsUseCase.getAllEvents().let {
                eventsLiveData.postValue(it)
            }
        }

    }

    fun removeAllEvents() {
        CoroutineScope(IO).launch {
            eventsUseCase.removeAllEvents()
            eventsLiveData = MutableLiveData()
        }
    }
}