package com.example.treinokotlin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.treinokotlin.data.dataSource.EventEntityDao
import com.example.treinokotlin.data.model.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun eventEntityDao(): EventEntityDao
}