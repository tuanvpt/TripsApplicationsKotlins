package com.example.tripsapplicationskotlins.ui.search

import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tripsapplicationskotlins.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {

    private val adapterSearch = SearchAdapter()

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentSearchBinding.inflate(inflater)

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }

    override fun setUpView() {

        val searchTrips = viewBinding.edtSearch.addTextChangedListener {
            viewModel.getSearchAllTrip(it.toString())
        }

        viewBinding.rvListSearchTrips.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterSearch
        }
    }

    override fun registerLiveData() {
        viewModel.getSearchTripObs.observe(this) {
            when (it) {
                is SearchViewModel.GetSearchTripObs.OnSuccess -> {
                    adapterSearch.setItems(it.items)
                }
                is SearchViewModel.GetSearchTripObs.OnFailure -> {
                    showLoading()
                }
            }
        }

    }
}