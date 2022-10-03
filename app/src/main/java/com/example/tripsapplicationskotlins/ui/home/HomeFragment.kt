package com.example.tripsapplicationskotlins.ui.home

import android.app.DatePickerDialog
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.lifecycle.ViewModelStoreOwner
import com.example.tripsapplicationskotlins.R
import com.example.tripsapplicationskotlins.base.BaseFragment
import com.example.tripsapplicationskotlins.database.entities.Trips
import com.example.tripsapplicationskotlins.databinding.FragmentHomeBinding
import com.example.tripsapplicationskotlins.utils.LogUtil
import com.example.tripsapplicationskotlins.utils.exts.onClickListenerDelay
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private var isAllFieldsChecked = false
    private val myCalendar = Calendar.getInstance()
    private var isCheckRequire = false
    private var checkString: String = ""

    override fun inflateViewBinding(inflater: LayoutInflater) =
        FragmentHomeBinding.inflate(inflater)

    override fun getViewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun setUpView() {
        handleCheckBox()
        handleDataOfTrips()
        handleSubmitButton()
        loadAdBanner()
    }

    private fun handleDataOfTrips() {
        val date =
            DatePickerDialog.OnDateSetListener { _: DatePicker?, year: Int, month: Int, day: Int ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }
        viewBinding.edtDateOfTrip.isClickable = true
        viewBinding.edtDateOfTrip.onClickListenerDelay() {
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
        with(viewBinding) {
            cbYes.setOnCheckedChangeListener { _, _ ->
                viewBinding.cbNo.isChecked = false
                isCheckRequire = true
            }
            cbNo.setOnCheckedChangeListener { _, _ ->
                viewBinding.cbYes.isChecked = false
                isCheckRequire = false
            }
        }
    }

    private fun handleSubmitButton() {
        viewBinding.btnDatabase.setOnClickListener {
            isAllFieldsChecked = isCheckAllFields()
            if (isAllFieldsChecked) {
                /*Add data to SQLite*/
                checkString = if (isCheckRequire) {
                    viewBinding.cbNo.text.toString()
                } else {
                    viewBinding.cbYes.text.toString()
                }
                val trips = Trips(
                    0,
                    viewBinding.edtName.text.toString(),
                    viewBinding.edtDestination.text.toString(),
                    viewBinding.edtDateOfTrip.text.toString(),
                    checkString,
                    viewBinding.edtDestination.text.toString()
                )
                viewModel.insert(trips)

                showToast("Saved")
            }
        }
    }

    override fun registerLiveData() {
        viewModel.insertTripObs.observe(this) {
            when (it) {
                is HomeViewModel.InsertTripObs.OnSuccess -> {
                    LogUtil.e("Saved successful")
                }
                is HomeViewModel.InsertTripObs.OnFailure -> {
                    LogUtil.e(it.toString())
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
            if (edtDateOfTrip.length() == 0) {
                edtDateOfTrip.error = getString(R.string.label_required_fill_info)
                return false
            }
            return true
        }
    }

    private fun updateLabel() {
        val myFormat = getString(R.string.label_format_month_day_year)
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        viewBinding.edtDateOfTrip.setText(dateFormat.format(myCalendar.time))
    }

    override fun getViewModelProviderOwner(): ViewModelStoreOwner {
        return this
    }

    /** AdMob demo */
    private fun loadAdBanner() {
        MobileAds.initialize(requireContext()) {}
        val adRequest = AdRequest.Builder().build()
        viewBinding.adView.loadAd(adRequest)
        viewBinding.adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                showToast("Ad Loaded")
                LogUtil.e("Ad Loaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                LogUtil.e(adError.toString())
            }
        }
    }
}