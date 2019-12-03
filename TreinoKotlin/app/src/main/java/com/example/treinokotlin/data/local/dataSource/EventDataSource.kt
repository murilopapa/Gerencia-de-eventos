package com.example.treinokotlin.data.local.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.treinokotlin.data.local.model.EventEntity

@Dao
interface EventEntityDao {

    @Query("SELECT * FROM EventEntity")
    fun all(): List<EventEntity>

    @Insert
    fun add(vararg event: EventEntity)

    @Query("DELETE FROM EventEntity")
    fun removeAll()

    @Insert
    fun addFromAPI(returnValue: List<EventEntity>)

}