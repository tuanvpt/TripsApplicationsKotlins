package com.example.tripsapplicationskotlins.ui.update

import com.example.tripsapplicationskotlins.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpdateViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {
}