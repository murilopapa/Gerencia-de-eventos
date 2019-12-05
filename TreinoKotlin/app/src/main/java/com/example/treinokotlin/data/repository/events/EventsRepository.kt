package com.example.treinokotlin.data.repository.events

import com.example.treinokotlin.data.local.dataSource.EventEntityDao
import com.example.treinokotlin.data.local.model.toEvent
import com.example.treinokotlin.data.remote.dataSource.EventRemoteDataSource
import com.example.treinokotlin.data.remote.model.EventWs
import com.example.treinokotlin.data.remote.model.toEntityEvent
import com.example.treinokotlin.data.remote.model.toEvent
import com.example.treinokotlin.model.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class EventsRepository(
    private val entityEventDao: EventEntityDao,
    private val eventRemoteDataSource: EventRemoteDataSource
) {

    suspend fun getEvents(): List<Event>? =
        withContext(Dispatchers.IO) {
            if (entityEventDao.all().isEmpty()) {
                println("Empty local database")

                val  wsEventList =
                    eventRemoteDataSource.getAllEvents("5de5a0942e0000880031fe22").execute().body()

                wsEventList?.let { eventWsList ->
                    entityEventDao.addFromAPI(eventWsList.toEntityEvent())
                    eventWsList.toEvent()
                }


            } else {
                println("Not empty local database")
                entityEventDao.all().toEvent()
            }
        }


    suspend fun removeAllEvents() {
        withContext(Dispatchers.IO) {
            entityEventDao.removeAll()
        }
    }

}