package com.example.tripsapplicationskotlins.ui.search

import androidx.lifecycle.MutableLiveData
import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import com.example.tripsapplicationskotlins.ui.all.AllViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {

    val getTripObs: MutableLiveData<AllViewModel.GetTripObs> = MutableLiveData()





}