package com.example.tripsapplicationskotlins.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.tripsapplicationskotlins.database.entities.Trip


@Dao
interface UserDao {
    @Query("SELECT * FROM trips")
    fun getAll(): List<Trip>

    //TODO check
/*    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Trip>*/

    //TODO check
/*    @Query(
        "SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )*/
    fun findByName(first: String, last: String): Trip

    @Insert
    fun insertAll(vararg users: Trip)

    @Delete
    fun delete(user: Trip)

}
