package com.xyfindables.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.xyfindables.core.XYBase

import com.xyfindables.ui.dialogs.XYThrobberDialog

open class XYBaseFragment : Fragment() {

    fun logInfo(message: String) {
        XYBase.logInfo(XYBaseFragment.TAG, message)
    }

    fun logExtreme(message: String) {
        XYBase.logExtreme(XYBaseFragment.TAG, message)
    }

    fun logError(message: String, debug: Boolean) {
        XYBase.logError(XYBaseFragment.TAG, message, debug)
    }

    fun logException(exception: Exception, debug: Boolean) {
        XYBase.logException(XYBaseFragment.TAG, exception, debug)
    }

    fun logException(tag: String, exception: Exception, debug: Boolean) {
        XYBase.logException(tag, exception, debug)
    }

    fun logStatus(tag: String, message: String, debug: Boolean) {
        XYBase.logError(tag, message, debug)
    }

    fun logInfo(tag: String, message: String) {
        XYBase.logInfo(tag, message)
    }

    fun logExtreme(tag: String, message: String) {
        XYBase.logExtreme(tag, message)
    }

    fun logError(tag: String, message: String, debug: Boolean) {
        XYBase.logError(tag, message, debug)
    }

    fun logStatus(message: String, debug: Boolean) {
        XYBase.logError(XYBaseFragment.TAG, message, debug)
    }

    private val _progressDialog: ProgressDialog? = null
    var throbber: XYThrobberDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        XYBase.logInfo(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        throbber = XYThrobberDialog(activity as Activity)
    }

    open fun loadViews() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        XYBase.logInfo(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViews()
    }

    open fun dpToPx(res: Resources, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), res.displayMetrics).toInt()
    }

    override fun onResume() {
        XYBase.logInfo(TAG, "onResume")
        super.onResume()
    }

    override fun onPause() {
        XYBase.logInfo(TAG, "onPause")
        super.onPause()
        throbber?.hide()
    }

    protected fun showToast(message: String) {
        XYBase.logInfo(TAG, "showToast")
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun onAttach(context: Context) {
        XYBase.logInfo(TAG, "onAttach")
        super.onAttach(context)
    }

    companion object {
        private val TAG = XYBaseFragment::class.java.simpleName
    }
}

