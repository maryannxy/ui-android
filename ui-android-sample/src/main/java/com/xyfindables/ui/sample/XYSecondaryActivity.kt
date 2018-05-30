package com.xyfindables.ui.sample

import android.os.Bundle

import com.crashlytics.android.Crashlytics
import com.xyfindables.ui.XYBaseActivity
import com.xyfindables.ui.views.XYToolbar

import io.fabric.sdk.android.Fabric

/**
 * Created by arietrouw on 12/29/17.
 */

class XYSecondaryActivity : XYBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondary_activity)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        toolbar()?.enableBackNavigation(this)
        super.onPostCreate(savedInstanceState)
    }
}
