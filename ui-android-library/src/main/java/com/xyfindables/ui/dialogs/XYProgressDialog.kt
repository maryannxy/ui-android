package com.xyfindables.ui.dialogs

import android.app.ProgressDialog
import android.content.Context

import com.xyfindables.ui.XYBaseActivity

import com.xyfindables.core.XYBase

class XYProgressDialog(context: Context) : ProgressDialog(context) {

    fun show(title: String, message: String, cancellable: Boolean, max: Int) {
        XYBase.logInfo(TAG, "show")
        XYBaseActivity.RunOnUIThread( Runnable {
            setTitle(title)
            setMessage(message)
            setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
            setCancelable(cancellable)
            setMax(max)
            show()
        })
    }

    override fun hide() {
        XYBase.logInfo(TAG, "hide")
        XYBaseActivity.RunOnUIThread (Runnable{
            if (isShowing) {
                super@XYProgressDialog.dismiss()
            }
        })
    }

    override fun show() {
        XYBase.logInfo(TAG, "show")
        XYBaseActivity.RunOnUIThread (Runnable{
            if (!isShowing) {
                super@XYProgressDialog.show()
            }
        })
    }

    fun setMessage(message: String) {
        XYBase.logInfo(TAG, "setMessage")
        XYBaseActivity.RunOnUIThread (Runnable{ super@XYProgressDialog.setMessage(message) })
    }

    override fun setMax(max: Int) {
        XYBase.logInfo(TAG, "setMax")
        XYBaseActivity.RunOnUIThread (Runnable{ super@XYProgressDialog.setMax(max) })
    }

    override fun setProgress(progress: Int) {
        XYBase.logInfo(TAG, "setProgress")
        XYBaseActivity.RunOnUIThread (Runnable{ super@XYProgressDialog.setProgress(progress) })
    }

    override fun incrementProgressBy(value: Int) {
        XYBase.logInfo(TAG, "incrementProgressBy")
        XYBaseActivity.RunOnUIThread (Runnable{ super@XYProgressDialog.incrementProgressBy(value) })
    }

    companion object {
        private val TAG = XYProgressDialog::class.java.simpleName
    }
}
