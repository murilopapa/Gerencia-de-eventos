package com.example.treinokotlin.data.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.treinokotlin.data.model.EventEntity

@Dao
interface EventEntityDao {

    @Query("SELECT * FROM EventEntity")
    fun all(): List<EventEntity>

    @Insert
    fun add(vararg event: EventEntity)

    @Query("DELETE FROM EventEntity")
    fun removeall()

    @Insert
    fun addFromAPI(returnValue: List<EventEntity>)

}