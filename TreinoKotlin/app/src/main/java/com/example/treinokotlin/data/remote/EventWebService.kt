package com.example.treinokotlin.data.remote

import com.example.treinokotlin.data.remote.model.EventWs
import com.example.treinokotlin.mechanism.GenericEvent
import retrofit2.Call
import retrofit2.http.GET

interface EventWebService {


    @GET("5dea4585300000eb432b070a")
    fun getAllEvents(): Call<GenericEvent<List<EventWs>>>


    @GET("5dee4639330000af47984607")
    fun getSingleEvent(): Call<GenericEvent<EventWs>>

}
