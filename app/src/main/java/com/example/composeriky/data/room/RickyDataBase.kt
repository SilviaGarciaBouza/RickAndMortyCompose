package com.example.composeriky.data.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.composeriky.data.RikyItemResponse

@Database(entities = [RikyItemResponse::class], version = 1)
abstract class RickyDataBase: RoomDatabase() {
    abstract fun taskDao(): RickyDao
}
