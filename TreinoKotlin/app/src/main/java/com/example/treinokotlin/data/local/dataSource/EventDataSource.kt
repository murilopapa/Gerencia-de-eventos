package com.example.treinokotlin.data.local.dataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.treinokotlin.data.local.model.EventEntity

@Dao
interface EventEntityDao {

    @Query("SELECT * FROM EventEntity")
    fun all(): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(event: EventEntity)

    @Query("DELETE FROM EventEntity")
    fun removeAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFromAPI(returnValue: List<EventEntity>)

}