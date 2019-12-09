package com.example.treinokotlin.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebServiceClient {
    var webService: EventWebService

    init {
        webService = createEventWebService()
    }

    private fun createEventWebService() = Retrofit.Builder()
        .baseUrl("http://www.mocky.io/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(EventWebService::class.java)


}