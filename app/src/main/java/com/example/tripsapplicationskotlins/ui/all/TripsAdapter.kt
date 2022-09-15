package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tripsapplicationskotlins.base.BaseAdapter
import com.example.tripsapplicationskotlins.database.entities.Trip
import com.example.tripsapplicationskotlins.databinding.TripsItemBinding


class TripsAdapter(
    private val onClick: (Trip) -> Unit,
    private val onEdit: (Trip) -> Unit,
    private val onDelete: (Trip) -> Unit
) : BaseAdapter<TripsItemBinding, Trip>() {

    override fun getLayoutBinding(parent: ViewGroup, viewType: Int): TripsItemBinding {
        return TripsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun addItem(fieldModel: Trip) {
        super.addItem(fieldModel)
    }

    override fun addItems(items: List<Trip>) {
        super.addItems(items)
    }

    override fun bind(binding: TripsItemBinding, item: Trip, position: Int) {
        binding.txtName.text = item.name
        binding.txtDestination.text = item.destination
        binding.txtDateOfTime.text = item.dataOfTrip

        binding.btnEdit.setOnClickListener { onEdit(item) }
        binding.btnDelete.setOnClickListener { onDelete(item) }
        binding.btnView.setOnClickListener { onClick(item) }

    }

}

