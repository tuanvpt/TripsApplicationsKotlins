package com.example.tripsapplicationskotlins.ui.all

import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.example.tripsapplicationskotlins.base.BaseDialog
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.EditDialogBinding

class EditDialog : BaseDialog<EditDialogBinding>() {

    override fun setUpView() {

    }

    //todo load data onresume

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    fun showText(fragmentManager: FragmentManager, trips: Trips) {
        show(fragmentManager, "Demo")

       // viewBinding.edtName.setText(trips.name)
/*        txtDestination.text = item.destination
        txtDateOfTime.text = item.dateOfTrip
        txtUid.text = item.id.toString()*/

    }

    override fun inflateViewBinding(inflater: LayoutInflater) =
        EditDialogBinding.inflate(inflater)


}




