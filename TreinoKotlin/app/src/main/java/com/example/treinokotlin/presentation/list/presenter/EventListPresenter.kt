package com.example.treinokotlin.presentation.list.presenter

import com.example.treinokotlin.domain.usecases.EventsUseCase
import com.example.treinokotlin.mechanism.livedata.MutableLiveDataResource
import com.example.treinokotlin.mechanism.livedata.Resource
import com.example.treinokotlin.model.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class EventListPresenter(private val eventsUseCase: EventsUseCase) {
    var eventsLiveData = MutableLiveDataResource<List<Event>>()

    fun getEvents() {
        CoroutineScope(IO).launch {
            eventsLiveData.postValue(Resource.loading())
            eventsUseCase.getAllEvents().let {
                eventsLiveData.postValue(Resource.success(it))
            }
        }

    }

    fun removeAllEvents() {
        CoroutineScope(IO).launch {
            eventsLiveData.postValue(Resource.loading())
            eventsUseCase.removeAllEvents()
            eventsLiveData.postValue(Resource.success())
            eventsLiveData = MutableLiveDataResource()

        }
    }
}