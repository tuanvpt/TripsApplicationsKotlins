package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.tripsapplicationskotlins.base.BaseAdapter
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.TripsItemBinding
import com.example.tripsapplicationskotlins.utils.exts.onClickListenerDelay


class TripsAdapter(
    private val onClick: (Trips) -> Unit,
    private val onEdit: (Trips) -> Unit,
    private val onDelete: (Trips) -> Unit,
) : BaseAdapter<TripsItemBinding, Trips>() {

    override fun getLayoutBinding(parent: ViewGroup, viewType: Int): TripsItemBinding {
        return TripsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    fun update(trips: Trips) {
        val index = items.indexOf(trips)
    }

    override fun bind(binding: TripsItemBinding, item: Trips, position: Int) {
        with(binding) {
            txtName.text = item.name
            txtDestination.text = item.destination
            txtDateOfTime.text = item.dateOfTrip
            txtUid.text = item.id.toString()

            btnView.onClickListenerDelay { onClick.invoke(item) }
            btnEdit.onClickListenerDelay { onEdit.invoke(item) }
            btnDelete.onClickListenerDelay { onDelete.invoke(item) }
        }
    }

}

