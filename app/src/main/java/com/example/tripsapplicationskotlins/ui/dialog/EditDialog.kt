package com.example.tripsapplicationskotlins.ui.dialog

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.tripsapplicationskotlins.base.BaseDialog
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.EditDialogBinding
import com.example.tripsapplicationskotlins.utils.LogUtil

class EditDialog(private val callback: (Trips) -> Unit) : BaseDialog<EditDialogBinding>() {

    private var trip: Trips? = null

    override fun inflateViewBinding(inflater: LayoutInflater) =
        EditDialogBinding.inflate(inflater)

    override fun setUpView() {
        viewBinding.btnSaveEdit.setOnClickListener {
            LogUtil.e("Trips position" + trip?.id.toString())
            trip?.apply {
                name = viewBinding.edtName.text.toString()
                destination = viewBinding.edtDestination.text.toString()
                dateOfTrip = null
                requireAssessment = viewBinding.cbYes.text.toString()
                description = viewBinding.edtDescription.text.toString()
                callback.invoke(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        with(viewBinding) {
            edtName.setText(trip?.name)
            edtDestination.setText(trip?.destination)
            edtDataOfTrip.setText(trip?.dateOfTrip)
            if (trip?.requireAssessment.equals("yes")) {
                cbYes.isChecked = true
            } else {
                cbNo.isChecked = true
            }
            edtDescription.setText(trip?.description)
        }
    }

    fun showText(fragmentManager: FragmentManager, trip: Trips) {
        show(fragmentManager, "Demo")
        this.trip = trip
    }

    override fun onPause() {
        super.onPause()
        if (dialog != null && dialog!!.isShowing) {
            dialog!!.dismiss()
        }
    }

}
