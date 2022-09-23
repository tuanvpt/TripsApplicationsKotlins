package com.example.tripsapplicationskotlins.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Utilities of View
 */
object ViewUtils {
    /**
     * Set click with delay of view
     *
     * @param view            view need to handle click
     * @param onClickListener response function when user clicked on view
     */
    fun setOnDelayClick(view: View, onClickListener: View.OnClickListener) {
        view.setOnClickListener {
            /*Disable click event of view*/
            view.isEnabled = false

            /*Handle click event*/
            onClickListener.onClick(view)

            /*Open view click */
            view.postDelayed({ view.isEnabled = true }, 500)
        }
    }

    /**
     * Change visibility of view to Visible
     *
     * @param view   view need to change visibility
     * @param isShow is showing view or not
     */
    fun changeVisibility(view: View, isShow: Boolean) {
        if (isShow) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }


    /**
     * Show dialog
     *
     * @param dialog dialog need to show
     */
    fun showDialog(dialog: Dialog) {
        /*Only show if dialog not showing*/
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    /**
     * Hide keyboard
     *
     * @param context context
     * @param view    view need focus
     */
    fun hideKeyboardFrom(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}