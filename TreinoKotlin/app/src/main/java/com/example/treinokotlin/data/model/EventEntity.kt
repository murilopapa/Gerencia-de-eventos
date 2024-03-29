package com.example.treinokotlin.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.treinokotlin.Event

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val date: String,
    val local: String,
    val description: String)

fun List<EventEntity>.toEvent(): List<Event> =
    map {
        Event(
            it.name,
            it.date,
            it.local,
            it.description
        )
    }