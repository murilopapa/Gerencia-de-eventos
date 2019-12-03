package com.example.treinokotlin.data.repository.events

import com.example.treinokotlin.model.Event
import com.example.treinokotlin.data.local.dataSource.EventEntityDao
import com.example.treinokotlin.data.local.model.EventEntity
import com.example.treinokotlin.data.local.model.toEvent
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class EventsRepository(
    private val entityEventDao: EventEntityDao
) {

    suspend fun getEvents(): List<Event> =
        withContext(Dispatchers.IO) {
            if (entityEventDao.all().isEmpty()) {
                println("Empty local database")

                val jsonStr =
                    java.net.URL("http://www.mocky.io/v2/5de5a0942e0000880031fe22").readText()
                val entityEventList: List<EventEntity> =
                    Gson().fromJson(jsonStr, Array<EventEntity>::class.java).toList()

                entityEventDao.addFromAPI(entityEventList)
                entityEventList.toEvent()

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