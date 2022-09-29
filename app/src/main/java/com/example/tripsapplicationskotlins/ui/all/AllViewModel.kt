package com.example.tripsapplicationskotlins.ui.all

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AllViewModel @Inject constructor(private val repository: TripRepository) : BaseViewModel() {

    val getTripObs: MutableLiveData<GetTripObs> = MutableLiveData()
    val getDeleteObs: MutableLiveData<DeleteTripObs> = MutableLiveData()

    sealed class DeleteTripObs {
        class OnSuccessDelete(val items: Trips) : DeleteTripObs()
        class OnFailureDelete : DeleteTripObs()
    }

    sealed class GetTripObs {
        class OnSuccess(val items: List<Trips>) : GetTripObs()
        class OnFailure() : GetTripObs()
    }

    fun deleteTrip(trips: Trips) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.deleteTrips(trips)
            }.onSuccess {
                getDeleteObs.postValue(DeleteTripObs.OnSuccessDelete(trips))
            }.onFailure {
                getDeleteObs.postValue(DeleteTripObs.OnFailureDelete())
            }
        }
    }

    fun getAllTrip() {
        viewModelScope.launch(Dispatchers.IO) {
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