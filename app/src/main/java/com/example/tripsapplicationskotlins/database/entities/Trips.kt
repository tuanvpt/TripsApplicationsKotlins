package com.example.tripsapplicationskotlins.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Trips")
data class Trips(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "destination")
    var destination: String?,
    @ColumnInfo(name = "date_of_trips")
    var dateOfTrip: String?,
    @ColumnInfo(name = "require_assessment")
    var requireAssessment: String?,
    @ColumnInfo(name = "description")
    var description: String?
)