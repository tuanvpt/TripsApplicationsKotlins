package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tripsapplicationskotlins.base.BaseAdapter
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.TripsItemBinding


class TripsAdapter(
    private val onClick: (Trips) -> Unit,
    private val onEdit: (Trips) -> Unit,
    private val onDelete: (Trips) -> Unit
) : BaseAdapter<TripsItemBinding, Trips>() {

    override fun getLayoutBinding(parent: ViewGroup, viewType: Int): TripsItemBinding {
        return TripsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun bind(binding: TripsItemBinding, item: Trips, position: Int) {
        binding.txtName.text = item.name
        binding.txtDestination.text = item.destination
        binding.txtDateOfTime.text = item.dateOfTrip
        binding.txtUid.text = item.id.toString()

        binding.btnEdit.setOnClickListener { onEdit(item) }
        binding.btnDelete.setOnClickListener { onDelete(item) }
        binding.btnView.setOnClickListener { onClick(item) }

    }

}

