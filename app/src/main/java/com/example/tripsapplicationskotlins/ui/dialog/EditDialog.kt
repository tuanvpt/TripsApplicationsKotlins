package com.example.tripsapplicationskotlins.ui.dialog

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.tripsapplicationskotlins.base.BaseDialog
import com.example.tripsapplicationskotlins.databinding.EditDialogBinding

class EditDialog(private val callback: (String) -> Unit) : BaseDialog<EditDialogBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater) =
        EditDialogBinding.inflate(inflater)

    override fun setUpView() {
        viewBinding.edtName.setText("sdasd")
        viewBinding.btnSaveEdit.setOnClickListener {
            callback.invoke("zdfdas")
            dismiss()
        }
    }

    fun showText(fragmentManager: FragmentManager) {
        show(fragmentManager, "Demo")
    }

    override fun onPause() {
        super.onPause()
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

}
