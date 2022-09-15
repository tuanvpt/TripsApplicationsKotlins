package com.example.tripsapplicationskotlins.database.repositories

import com.example.tripsapplicationskotlins.database.daos.TripsDao
import com.example.tripsapplicationskotlins.database.entities.Trip
import javax.inject.Inject

class TripRepository @Inject constructor(private val tripsDao: TripsDao) {

    suspend fun insert(trips: Trip) = tripsDao.insertTrips(trips)

    suspend fun delete(trips: Trip) = tripsDao.delete(trips)

    suspend fun getAllItems(): List<Trip> = tripsDao.getAll()
}