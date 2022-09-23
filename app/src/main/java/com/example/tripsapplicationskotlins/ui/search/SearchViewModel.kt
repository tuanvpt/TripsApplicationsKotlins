package com.example.tripsapplicationskotlins.ui.search

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
class SearchViewModel @Inject constructor(private val repository: TripRepository) :
    BaseViewModel() {

    val getSearchTripObs: MutableLiveData<GetSearchTripObs> = MutableLiveData()

    sealed class GetSearchTripObs {
        class OnSuccess(val items: List<Trips>) : GetSearchTripObs()
        class OnFailure() : GetSearchTripObs()
    }

    fun getSearchAllTrip(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                repository.searchNameTrips(name)
            }.onSuccess {
                getSearchTripObs.postValue(GetSearchTripObs.OnSuccess(it))
            }.onFailure {
                getSearchTripObs.postValue(GetSearchTripObs.OnFailure())
            }
        }
    }


}