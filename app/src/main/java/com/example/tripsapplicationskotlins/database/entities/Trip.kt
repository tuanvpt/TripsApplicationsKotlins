package com.example.tripsapplicationskotlins.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "TRIPS")
data class Trip(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "destination") val destination: String?,
    @ColumnInfo(name = "date_of_trips") val dataOfTrip: String?,
    @ColumnInfo(name = "require_assessment") val requireAssessment: String?,
    @ColumnInfo(name = "description") val description: String?
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var id: Int = 0
}
