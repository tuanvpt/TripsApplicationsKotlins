package com.example.tripsapplicationskotlins.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tripsapplicationskotlins.database.TripDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependence injection of App module
 */
@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TripDatabase =
        Room.databaseBuilder(
            context,
            TripDatabase::class.java,
            "Trips_database"
        ).build()


    @Provides
    @Singleton
    fun provideTripsDao(database: TripDatabase) = database.tripDao()
}
