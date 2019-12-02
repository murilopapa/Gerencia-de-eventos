package com.example.treinokotlin.domain.usecases

import com.example.treinokotlin.Event
import com.example.treinokotlin.data.repository.events.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventsUseCase(
    private val repository: EventsRepository
) {

    suspend fun getAllEvents(): List<Event> =
        withContext(Dispatchers.IO) {
            repository.getEvents()
        }
    suspend fun removeAllEvents() {
        repository.removeAllEvents()
    }

}