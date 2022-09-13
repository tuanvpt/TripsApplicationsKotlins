package com.example.tripsapplicationskotlins.ui.home

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.R
import com.example.tripsapplicationskotlins.databinding.FragmentHomeBinding
import com.example.tripsapplicationskotlins.utils.ViewUtils
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var isAllFieldsChecked = false
    private val myCalendar = Calendar.getInstance()

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    override fun setUpView() {
        viewBinding.root.setOnClickListener { _ ->
            ViewUtils.hideKeyboardFrom(
                requireContext(),
                viewBinding.root
            )
            handleDataOfTrips()
            handleSubmitButton()
            handleCheckBox()

        }
    }

    private fun handleDataOfTrips() {
        val date =
            DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, day: Int ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        viewBinding.edtDataOfTrip.setClickable(true)
        viewBinding.edtDataOfTrip.setOnClickListener { view ->
            DatePickerDialog(
                requireContext(),
                date,
                myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }

    private fun handleCheckBox() {
        viewBinding.cbYes.setOnCheckedChangeListener { _, _ ->
            viewBinding.cbNo.isChecked = false
        }
        viewBinding.cbNo.setOnCheckedChangeListener { _, _ ->
            viewBinding.cbYes.isChecked = false
        }
    }

    private fun handleSubmitButton() {
        viewBinding.btnDatabase.setOnClickListener {
            isAllFieldsChecked = CheckAllFields()
            if (isAllFieldsChecked) {
                // Save data in SQLite
                showToast("Saved")
            }
        }
    }

    private fun CheckAllFields(): Boolean {
        if (viewBinding.edtName.length() == 0) {
            viewBinding.edtName.error = "This field is required"
            return false
        }
        if (viewBinding.edtDestination.length() == 0) {
            viewBinding.edtDestination.error = "This field is required"
            return false
        }
        if (viewBinding.edtDataOfTrip.length() == 0) {
            viewBinding.edtDataOfTrip.error = "This field is required"
            return false
        }

        if (viewBinding.cbYes.isChecked || viewBinding.cbNo.isChecked) {
            ViewUtils.show(viewBinding.tvErrorCheckRisk);
            viewBinding.tvErrorCheckRisk.setText(R.string.label_validation_risk);
            return false;
        }

        // after all validation return true.
        return true
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        viewBinding.edtDataOfTrip.setText(dateFormat.format(myCalendar.time))
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }
}