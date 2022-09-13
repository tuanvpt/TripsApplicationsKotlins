package com.example.tripsapplicationskotlins.database.daos

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tripsapplicationskotlins.database.entities.Trip

// database version
const val DB_VERSION = 1

@Database(entities = [Trip::class], version = DB_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): Trip
}