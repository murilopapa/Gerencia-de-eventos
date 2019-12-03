package com.example.treinokotlin.model

import java.io.Serializable

data class Event(
    val name: String,
    val date: String,
    val local: String,
    val description: String
): Serializable