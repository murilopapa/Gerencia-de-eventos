package com.example.treinokotlin.data.remote.dataSource

import com.example.treinokotlin.data.remote.EventWebService

class EventRemoteDataSource(private val webService: EventWebService) {
    fun getAllEvents() = webService.getAllEvents().execute().body()?.data ?: emptyList()
    fun getSingleEvent() = webService.getSingleEvent().execute().body()?.data
}