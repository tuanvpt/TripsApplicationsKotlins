package com.example.tripsapplicationskotlins.ui.search

import com.example.comic.utils.base.BaseViewModel
import com.example.tripsapplicationskotlins.database.repositories.TripRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: TripRepository) : BaseViewModel() {
}