package com.example.tripsapplicationskotlins.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.tripsapplicationskotlins.database.entities.Trip


@Dao
interface TripsDao {
    @Query("SELECT * FROM trips")
    suspend fun getAll(): List<Trip>

    @Query("SELECT * FROM trips WHERE uid IN (:tripIds)")
    suspend fun loadAllByIds(tripIds: IntArray): List<Trip>

    @Query(
        "SELECT * FROM trips WHERE name LIKE :name AND " +
                "destination LIKE :destination AND " +
                "date_of_trips LIKE :dataOfTrip AND " +
                "require_assessment LIKE :requireAssessment AND " +
                "description LIKE :description LIMIT 1"
    )
    suspend fun findByName(
        name: String,
        destination: String,
        dataOfTrip: String,
        requireAssessment: String,
        description: String
    ): Trip

    @Insert
    suspend fun insertTrips(trips: Trip)

    @Update(onConflict = REPLACE)
    suspend fun updateTrips(vararg trips: Trip)

    @Delete
    suspend fun delete(user: Trip)
}
