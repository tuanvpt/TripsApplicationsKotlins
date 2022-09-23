package com.example.tripsapplicationskotlins.ui.home

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseFragment
import com.example.tripsapplicationskotlins.R
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.FragmentHomeBinding
import com.example.tripsapplicationskotlins.utils.LogUtil
import com.example.tripsapplicationskotlins.utils.ViewUtils
import com.example.tripsapplicationskotlins.utils.exts.onClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var isAllFieldsChecked = false
    private val myCalendar = Calendar.getInstance()

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun setUpView() {
        viewBinding.root.onClickListener {
            ViewUtils.hideKeyboardFrom(
                requireContext(),
                viewBinding.root
            )
            LogUtil.e("MainActivity: ${viewModel.insertTripObs} , $viewModel")
            handleDataOfTrips()
            handleCheckBox()
            handleSubmitButton()
        }
    }

    private fun handleDataOfTrips() {
        val date =
            DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        viewBinding.edtDataOfTrip.apply {
            isClickable = true
            onClickListener() {
                DatePickerDialog(
                    requireContext(),
                    date,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH],
                    myCalendar[Calendar.DAY_OF_MONTH]
                ).show()
            }
        }
    }

    private fun handleCheckBox() {
        with(viewBinding) {
            cbYes.setOnCheckedChangeListener { _, _ ->
                viewBinding.cbNo.isChecked = false
            }
            cbNo.setOnCheckedChangeListener { _, _ ->
                viewBinding.cbYes.isChecked = false
            }
        }
    }

    private fun handleSubmitButton() {
        with(viewBinding) {
            btnDatabase.setOnClickListener {
                isAllFieldsChecked = isCheckAllFields()
                if (isAllFieldsChecked) {
                    /*Add data to SQLite*/
                    val trips = Trips(
                        0,
                        edtName.text.toString(),
                        edtDestination.text.toString(),
                        edtDataOfTrip.text.toString(),
                        cbYes.text.toString(),
                        edtDestination.text.toString()
                    )
                    viewModel.insert(trips)

                    showToast("Saved")
                }
            }
        }
    }

    private fun isCheckAllFields(): Boolean {
        with(viewBinding) {
            if (edtName.length() == 0) {
                edtName.error = getString(R.string.label_required_fill_info)
                return false
            }
            if (edtDestination.length() == 0) {
                edtDestination.error = getString(R.string.label_required_fill_info)
                return false
            }
            if (edtDataOfTrip.length() == 0) {
                edtDataOfTrip.error = getString(R.string.label_required_fill_info)
                return false
            }
            return true
        }
    }

    private fun updateLabel() {
        val myFormat = getString(R.string.label_format_month_day_year)
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        viewBinding.edtDataOfTrip.setText(dateFormat.format(myCalendar.time))
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }
}