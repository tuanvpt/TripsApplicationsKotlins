package com.example.tripsapplicationskotlins.database.repositories

import com.example.tripsapplicationskotlins.database.daos.TripsDao
import com.example.tripsapplicationskotlins.database.entities.Trips
import javax.inject.Inject

class TripRepository @Inject constructor(private val tripsDao: TripsDao) {

    suspend fun insert(trips: Trips) = tripsDao.insertTrips(trips)

    suspend fun delete(trips: Trips) = tripsDao.delete(trips)

    suspend fun getAllItems(): List<Trips> = tripsDao.getAll()

    fun searchNameTrips(name:String) : List<Trips> = tripsDao.getSearchedTrips(name)
}