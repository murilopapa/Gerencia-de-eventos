package com.example.treinokotlin.data.repository.events

import com.example.treinokotlin.Event
import com.example.treinokotlin.data.dataSource.EventEntityDao
import com.example.treinokotlin.data.model.EventEntity
import com.example.treinokotlin.data.model.toEvent
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
                val jsonStr = java.net.URL("http://www.mocky.io/v2/5de5603d2e0000880031fd57").readText()

                val homedateList: List<EventEntity> = Gson().fromJson(jsonStr, Array<EventEntity>::class.java).toList()

                println(listOf(homedateList))
                entityEventDao.addFromAPI(homedateList)
                homedateList.toEvent()

            } else {
                println("Not empty local database")
                entityEventDao.all().toEvent()
            }
        }


    fun removeAllEvents() {
        entityEventDao.removeall()
    }

}