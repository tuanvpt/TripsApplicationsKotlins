package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.database.entities.Trip
import com.example.tripsapplicationskotlins.databinding.FragmentAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentAllBinding, AllViewModel>() {


    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentAllBinding.inflate(inflater)

    override fun setUpView() {
        val adapter = TripsAdapter(onClick, onEdit, onDelete)
        viewBinding.rvListTrips.setHasFixedSize(true)
        viewBinding.rvListTrips.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.rvListTrips.adapter = adapter

    }

    private val onClick: (Trip) -> Unit = {
        /*     val intent = Intent(requireContext(), UpdateNoteActivity::class.java)
             intent.putExtra("UPDATE_NOTE", it)
             startActivity(intent)*/
        showToast("view")

    }

    private val onEdit: (Trip) -> Unit = {
        showToast("edit")

    }

    private val onDelete: (Trip) -> Unit = {
        viewModel.deleteTrip(it)
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<AllViewModel> {
        return AllViewModel::class.java
    }
}
