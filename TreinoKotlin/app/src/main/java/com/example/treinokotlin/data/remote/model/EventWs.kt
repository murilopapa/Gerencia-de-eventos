package com.example.treinokotlin.data.remote.model


import com.example.treinokotlin.data.local.model.EventEntity
import com.example.treinokotlin.model.Event
import java.io.Serializable

data class EventWs(
    val id: Long = 0,
    val name: String,
    val date: String,
    val local: String,
    val description: String
) : Serializable

fun List<EventWs>.toEventEntity(): List<EventEntity> =
    map {
        EventEntity(
            it.id,
            it.name,
            it.date,
            it.local,
            it.description
        )
    }

fun EventWs.toEventEntity(): EventEntity =
    let {
        EventEntity(
            it.id,
            it.name,
            it.date,
            it.local,
            it.description
        )
    }

fun List<EventWs>.toEvent(): List<Event> =
    map {
        Event(
            it.name,
            it.date,
            it.local,
            it.description
        )
    }

fun EventWs.toEvent(): Event =
    let {
        Event(
            it.name,
            it.date,
            it.local,
            it.description
        )
    }