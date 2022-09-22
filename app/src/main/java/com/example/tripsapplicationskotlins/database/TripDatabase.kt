package com.example.tripsapplicationskotlins.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tripsapplicationskotlins.database.daos.TripsDao
import com.example.tripsapplicationskotlins.database.entities.Trips

// database version
const val DB_VERSION = 1

/** Annotates class to be a Room Database with a table (entity) of the Trip class*/
@Database(
    entities = [Trips::class],
    version = DB_VERSION
)
abstract class TripDatabase : RoomDatabase() {
    abstract fun tripDao(): TripsDao
}