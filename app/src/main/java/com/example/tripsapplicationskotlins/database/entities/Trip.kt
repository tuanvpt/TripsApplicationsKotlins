package com.example.tripsapplicationskotlins.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TRIPS")
data class Trip(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "NAME") val NAME: String?,
    @ColumnInfo(name = "DESTINATION") val DESTINATION: String?,
    @ColumnInfo(name = "DATEOFTRIPS") val DOTS: String?,
    @ColumnInfo(name = "REQUIREASSESSEMENT") val REQUIREASSESSEMENT: String?,
    @ColumnInfo(name = "DESCRIPTION") val DESCRIPTION: String?
)
