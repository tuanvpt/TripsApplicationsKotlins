package com.example.tripsapplicationskotlins.ui.all

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentAllBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllFragment : BaseFragment<FragmentAllBinding, AllViewModel>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentAllBinding.inflate(inflater)

    override fun setUpView() {
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<AllViewModel> {
        return AllViewModel::class.java
    }
}
