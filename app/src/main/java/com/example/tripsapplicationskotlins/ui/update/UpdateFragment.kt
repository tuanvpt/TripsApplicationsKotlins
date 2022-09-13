package com.example.tripsapplicationskotlins.ui.update

import android.view.LayoutInflater
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.databinding.FragmentUpdateBinding

class UpdateFragment : BaseFragment<FragmentUpdateBinding, UpdateModel>() {

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentUpdateBinding.inflate(inflater)

    override fun setUpView() {
    }

    companion object {
        fun newInstance() = UpdateFragment()
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<UpdateModel> {
        return UpdateModel::class.java
    }
}
