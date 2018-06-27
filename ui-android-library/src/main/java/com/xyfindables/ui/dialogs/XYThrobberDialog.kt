package com.xyfindables.ui.dialogs

/*
 Created by arietrouw on 12/27/17.
 */

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window

import com.xyfindables.ui.R
import com.xyfindables.ui.XYBaseActivity

import com.xyfindables.core.XYBase

class XYThrobberDialog(private val _activity: Activity) : Dialog(_activity) {

    init {
        setContentView(R.layout.dialog_throbber)
        val window = this.window
        if (window != null) {
            this.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

            val dividerId = this.context.resources.getIdentifier("android:id/titleDivider", null, null)
            val divider = this.findViewById<View>(dividerId)
            if (divider != null) {
                divider.visibility = View.GONE
            }
        }
    }

    companion object {

        private val TAG = XYThrobberDialog::class.java.simpleName
    }
}
