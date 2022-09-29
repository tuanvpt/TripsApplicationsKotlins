package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.FragmentAllBinding
import com.example.tripsapplicationskotlins.ui.all.AllViewModel.DeleteTripObs
import com.example.tripsapplicationskotlins.ui.all.AllViewModel.DeleteTripObs.OnFailureDelete
import com.example.tripsapplicationskotlins.ui.all.AllViewModel.GetTripObs.OnFailure
import com.example.tripsapplicationskotlins.ui.all.AllViewModel.GetTripObs.OnSuccess
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentAllBinding, AllViewModel>() {

    private val adapterTrips = TripsAdapter(::onClick, ::onEdit, ::onDelete)

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentAllBinding.inflate(inflater)

    override fun setUpView() {
        viewModel.getAllTrip()
        viewBinding.rvListAllTrips.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = adapterTrips
        }
    }

    private fun onClick(trips: Trips) {
        showToast("view")
    }

    private fun onEdit(trips: Trips) {
        showToast("edit")
    }

    private fun onDelete(trips: Trips) {
        viewModel.deleteTrip(trips)
        showToast("Delete")

    }

    override fun registerLiveData() {
        viewModel.getTripObs.observe(this) {
            when (it) {
                is OnSuccess -> {
                    adapterTrips.setItems(it.items)
                }
                is OnFailure -> {
                    viewBinding.txtEmpty.isVisible
                }
            }
        }

        viewModel.getDeleteObs.observe(this) {
            when (it) {
                is DeleteTripObs.OnSuccessDelete -> {
                    adapterTrips.removeItem(it.items)
                }
                is OnFailureDelete -> {
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
