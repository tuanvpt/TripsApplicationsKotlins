package com.example.tripsapplicationskotlins.ui.search

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {


    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentSearchBinding.inflate(inflater)

    override fun setUpView() {
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<SearchViewModel> {
        return SearchViewModel::class.java
    }
}