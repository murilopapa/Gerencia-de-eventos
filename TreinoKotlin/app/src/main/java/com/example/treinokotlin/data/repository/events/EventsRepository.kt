package com.example.treinokotlin.data.repository.events

import com.example.treinokotlin.data.local.dataSource.EventEntityDao
import com.example.treinokotlin.data.local.model.toEvent
import com.example.treinokotlin.data.remote.dataSource.EventRemoteDataSource
import com.example.treinokotlin.data.remote.model.toEvent
import com.example.treinokotlin.data.remote.model.toEventEntity
import com.example.treinokotlin.mechanism.GenericEvent
import com.example.treinokotlin.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class EventsRepository(
    private val entityEventDao: EventEntityDao,
    private val eventRemoteDataSource: EventRemoteDataSource
) {

    suspend fun getEvents(): GenericEvent<List<Event>> =
        withContext(Dispatchers.IO) {
            if (entityEventDao.all().isEmpty()) {
                println("Empty local database")

                eventRemoteDataSource.getAllEvents().let {
                    entityEventDao.addFromAPI(it.toEventEntity())
                    GenericEvent(it.toEvent())
                }
            } else {
                println("Not empty local database")
                GenericEvent(entityEventDao.all().toEvent())
            }

        }

    suspend fun removeAllEvents() {
        withContext(Dispatchers.IO) {
            entityEventDao.removeAll()
        }
    }

}