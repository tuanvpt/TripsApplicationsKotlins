package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.FragmentAllBinding
import com.example.tripsapplicationskotlins.ui.all.AllViewModel.GetTripObs.OnFailure
import com.example.tripsapplicationskotlins.ui.all.AllViewModel.GetTripObs.OnSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentAllBinding, AllViewModel>() {

    private val adapter = TripsAdapter(::onClick, ::onEdit, ::onDelete)

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentAllBinding.inflate(inflater)

    override fun setUpView() {
        viewModel.getAllTrip()
        viewBinding.rvListTrips.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }
    }

    private fun onClick(trips: Trips) {
        showToast("view")
    }

    private fun onEdit(trips: Trips) {
        showToast("edit")
    }

    private fun onDelete(trips: Trips) {
        showToast("delete")
        viewModel.deleteTrip(trips)
        adapter.removeItem(trips)
    }

    override fun registerLiveData() {
        viewModel.getTripObs.observe(this) {
            when (it) {
                is OnSuccess -> {
                    adapter.setItems(it.items)

                }
                is OnFailure -> {
                    viewBinding.txtEmpty.isVisible
                }
            }
        }
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<AllViewModel> {
        return AllViewModel::class.java
    }
}
