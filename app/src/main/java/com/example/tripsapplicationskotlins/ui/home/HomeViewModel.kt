package com.example.tripsapplicationskotlins.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tripsapplicationskotlins.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: TripRepository) : BaseViewModel() {

    val insertTripObs: MutableLiveData<InsertTripObs> = MutableLiveData()

    sealed class InsertTripObs {
        class OnSuccess(val items: Trips) : InsertTripObs()
        class OnFailure(val error: Throwable) : InsertTripObs()
    }

    fun insert(trips: Trips) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.insert(trips)
            }.onSuccess {
                insertTripObs.postValue(InsertTripObs.OnSuccess(trips))
            }.onFailure {
                insertTripObs.postValue(InsertTripObs.OnFailure(it))
            }
        }
    }

}