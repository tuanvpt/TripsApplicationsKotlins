package com.example.tripsapplicationskotlins.ui.all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {

    val getTripObs: MutableLiveData<GetTripObs> = MutableLiveData()

    sealed class DeleteTripObs {
        class OnSuccess : DeleteTripObs()
        class OnFailure : DeleteTripObs()
    }

    sealed class GetTripObs {
        class OnSuccess(val items: List<Trips>) : GetTripObs()
        class OnFailure() : GetTripObs()
    }

    fun deleteTrip(trips: Trips) = viewModelScope.launch {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.delete(trips)
            }.onSuccess {
            }.onFailure {
            }
        }
    }

    fun getAllTrip() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAllItems()
            }.onSuccess {
                getTripObs.postValue(GetTripObs.OnSuccess(it))
            }.onFailure {
                getTripObs.postValue(GetTripObs.OnFailure())
            }
        }
    }
}