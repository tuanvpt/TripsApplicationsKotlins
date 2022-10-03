package com.example.tripsapplicationskotlins.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// https://stackoverflow.com/questions/66897173/android-room-one-to-many-relationship

@Entity(
    tableName = "Expenses",
    foreignKeys = [
        ForeignKey(
            entity = Trips::class,
            parentColumns = ["id"],
            childColumns = ["tripId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class Expenses(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var id: Int = 0,
    @ColumnInfo(name = "tripId", index = true)
    var tripId: String?,
    @ColumnInfo(name = "type")
    var type: String?,
    @ColumnInfo(name = "amount")
    var amount: String?,
    @ColumnInfo(name = "time_of_expense")
    var timeOfExpense: String?,
)