package com.example.tripsapplicationskotlins.ui.all

import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AllViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {


}