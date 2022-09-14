package com.example.tripsapplicationskotlins.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.entities.Trip
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {

    val insertTripObs: MutableLiveData<InsertTripObs> = MutableLiveData()

    sealed class InsertTripObs {
        class OnSuccess : InsertTripObs()
        class OnFailure(val error: Throwable) : InsertTripObs()
    }

    fun insert(trips: Trip) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.insert(trips)
            }.onSuccess {
                insertTripObs.postValue(InsertTripObs.OnSuccess())
            }.onFailure {
                insertTripObs.postValue(InsertTripObs.OnFailure(it))
            }
        }
    }

    /*  fun delete(trips: Trip) = viewModelScope.launch {
          repository.delete(trips)
      }

      fun getAllItems() = repository.getAllItems()
  */
}