package com.example.tripsapplicationskotlins.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tripsapplicationskotlins.database.daos.TripsDao
import com.example.tripsapplicationskotlins.database.entities.Trip

// database version
const val DB_VERSION = 1

/** Annotates class to be a Room Database with a table (entity) of the Trip class*/
@Database(
    entities = [Trip::class],
    version = DB_VERSION
)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripsDao
}