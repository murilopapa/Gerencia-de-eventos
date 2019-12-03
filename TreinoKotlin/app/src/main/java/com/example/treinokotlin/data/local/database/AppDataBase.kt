package com.example.treinokotlin.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.treinokotlin.data.local.dataSource.EventEntityDao
import com.example.treinokotlin.data.local.model.EventEntity

@Database(entities = [EventEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun eventEntityDao(): EventEntityDao
}