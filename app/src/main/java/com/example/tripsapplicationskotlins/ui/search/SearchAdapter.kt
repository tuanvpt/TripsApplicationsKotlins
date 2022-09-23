package com.example.tripsapplicationskotlins.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tripsapplicationskotlins.base.BaseAdapter
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.TripsItemBinding

class SearchAdapter : BaseAdapter<TripsItemBinding, Trips>() {
    override fun getLayoutBinding(parent: ViewGroup, viewType: Int): TripsItemBinding {
        return TripsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: TripsItemBinding, item: Trips, position: Int) {
        with(binding) {
            txtName.text = item.name
            txtDestination.text = item.destination
            txtDateOfTime.text = item.dateOfTrip
            txtUid.text = item.id.toString()
        }
    }
}