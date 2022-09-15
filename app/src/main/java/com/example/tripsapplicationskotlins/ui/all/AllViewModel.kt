package com.example.tripsapplicationskotlins.ui.all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.entities.Trip
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {

    val deleteTripObs: MutableLiveData<DeleteTripObs> = MutableLiveData()

    sealed class DeleteTripObs {
        class OnSuccess : DeleteTripObs()
        class OnFailure : DeleteTripObs()
    }

    fun deleteTrip(trip: Trip) = viewModelScope.launch {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.delete(trip)
            }.onSuccess {
                deleteTripObs.postValue(DeleteTripObs.OnSuccess())
            }.onFailure {
                deleteTripObs.postValue(DeleteTripObs.OnFailure())
            }
        }
    }

    suspend fun getAllTrip(): List<Trip> = repository.getAllItems()

}