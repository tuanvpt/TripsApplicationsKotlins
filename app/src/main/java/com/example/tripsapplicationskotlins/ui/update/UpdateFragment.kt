package com.example.tripsapplicationskotlins.ui.update

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentUpdateBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : BaseFragment<FragmentUpdateBinding, UpdateViewModel>() {

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentUpdateBinding.inflate(inflater)

    override fun setUpView() {
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<UpdateViewModel> {
        return UpdateViewModel::class.java
    }
}
