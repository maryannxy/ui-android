package com.xyfindables.ui.dialogs

import android.app.ProgressDialog
import android.content.Context

import com.xyfindables.ui.XYBaseActivity

import com.xyfindables.core.XYBase

class XYProgressDialog(context: Context) : ProgressDialog(context) {

    fun show(title: String, message: String, cancellable: Boolean, max: Int) {
        XYBase.logInfo(TAG, "show")
        setTitle(title)
        setMessage(message)
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        setCancelable(cancellable)
        setMax(max)
        show()
    }

    override fun hide() {
        XYBase.logInfo(TAG, "hide")
        if (isShowing) {
            super@XYProgressDialog.dismiss()
        }
    }

    override fun show() {
        XYBase.logInfo(TAG, "show")
        if (!isShowing) {
            super@XYProgressDialog.show()
        }
    }

    companion object {
        private val TAG = XYProgressDialog::class.java.simpleName
    }
}
