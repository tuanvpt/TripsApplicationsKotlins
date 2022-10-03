package com.example.tripsapplicationskotlins.database.daos

import androidx.room.*
import com.example.tripsapplicationskotlins.database.entities.Trips


@Dao
interface TripsDao {
    @Query("SELECT * FROM trips")
    suspend fun getAll(): List<Trips>

    @Query("SELECT * FROM trips WHERE uid IN (:tripIds)")
    suspend fun loadAllByIds(tripIds: IntArray): List<Trips>

    @Query(
        "SELECT * FROM trips WHERE name LIKE :name AND " +
                "destination LIKE :destination AND " +
                "date_of_trips LIKE :dateOfTrips AND " +
                "require_assessment LIKE :requireAssessment AND " +
                "description LIKE :description LIMIT 1"
    )
    suspend fun findByName(
        name: String,
        destination: String,
        dateOfTrips: String,
        requireAssessment: String,
        description: String,
    ): Trips

    @Insert
    suspend fun insertTrips(trips: Trips)

    @Update
    suspend fun updateTrips(trips: Trips)

    @Delete
    suspend fun deleteTrips(trips: Trips)

    @Query("SELECT * FROM trips WHERE name LIKE '%' || :name || '%'")
    fun getSearchedTrips(name: String): List<Trips>

}
