package com.xyfindables.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.xyfindables.ui.dialogs.XYProgressDialog
import com.xyfindables.ui.dialogs.XYSplashDialog
import com.xyfindables.ui.dialogs.XYThrobberDialog
import com.xyfindables.ui.views.XYToolbar
import com.xyfindables.core.XYBase

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

import io.fabric.sdk.android.Fabric

abstract class XYBaseActivity : AppCompatActivity() {

    var _toolbar: XYToolbar? = null

    var throbber: XYThrobberDialog? = null
    var progress: XYProgressDialog? = null


    private var _dialogSplash: XYSplashDialog? = null
    fun toolbar(): XYToolbar? {
        if (_toolbar == null) {
            _toolbar = findViewById(R.id.toolbar)
        }
        return _toolbar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        XYBase.logStatus(TAG, "Activity Created: " + this.localClassName)
        Fabric.with(this, Answers(), Crashlytics())
        throbber = XYThrobberDialog(this)
        progress = XYProgressDialog(this)
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onResume() {
        XYBase.logStatus(TAG, "Activity Resumed: " + this.localClassName)
        super.onResume()
        _activityCount++
        XYBase.logInfo(TAG, "onResume:" + _activityCount + ":" + this.localClassName)
    }

    public override fun onStart() {
        XYBase.logStatus(TAG, "Activity Started: " + this.localClassName)
        super.onStart()
    }

    public override fun onStop() {
        XYBase.logStatus(TAG, "Activity Stopped: " + this.localClassName)
        super.onStop()
        _activityCount--
    }

    override fun onDestroy() {
        XYBase.logStatus(TAG, "Activity Destroyed: " + this.localClassName)
        super.onDestroy()
    }

    override fun onPause() {
        XYBase.logStatus(TAG, "Activity Paused: " + this.localClassName)
        super.onPause()
        throbber?.hide()
        hideKeyboard()
    }

    protected fun showSplash() {
        XYBase.logInfo(TAG, "showSplash")
        runOnUiThread {
            if (_dialogSplash == null) {
                _dialogSplash = XYSplashDialog(this@XYBaseActivity)
                if (!isFinishing) {
                    _dialogSplash!!.show()
                }
            }
        }
    }

    protected fun hideSplash() {
        XYBase.logInfo(TAG, "hideSplash")
        runOnUiThread {
            if (_dialogSplash != null) {
                _dialogSplash!!.dismiss()
                _dialogSplash = null
            }
        }
    }

    fun showToast(message: String) {
        XYBase.logInfo(TAG, "showProgressBar")
        runOnUiThread { Toast.makeText(this@XYBaseActivity, message, Toast.LENGTH_LONG).show() }
    }

    fun hideKeyboard() {
        XYBase.logInfo(TAG, "hideKeyboard")
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {

        private val TAG = XYBaseActivity::class.java.simpleName

        fun RunOnUIThread(action: Runnable) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                action.run()
            } else {
                val handler = Handler(Looper.getMainLooper())
                handler.post(action)
            }
        }

        protected val _threadPool: ThreadPoolExecutor

        init {
            _threadPool = ThreadPoolExecutor(3, 30, 30, TimeUnit.SECONDS, LinkedBlockingQueue())
        }

        protected var _activityCount = 0

        val isForeground: Boolean
            get() = _activityCount > 0
    }
}

