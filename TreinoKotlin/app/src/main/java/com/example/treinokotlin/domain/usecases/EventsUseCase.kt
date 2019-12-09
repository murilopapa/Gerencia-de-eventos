package com.example.treinokotlin.domain.usecases

import com.example.treinokotlin.model.Event
import com.example.treinokotlin.data.repository.events.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventsUseCase(
    private val repository: EventsRepository
) {

    suspend fun getAllEvents(): List<Event> =
        withContext(Dispatchers.IO) {
            repository.getEvents().data?.let {
                it
            } ?: emptyList()
        }

    suspend fun removeAllEvents() {
        withContext(Dispatchers.IO) {
            repository.removeAllEvents()
        }
    }

}