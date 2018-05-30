package com.xyfindables.ui.sample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.xyfindables.ui.XYBaseActivity
import com.xyfindables.ui.views.XYButton
import com.xyfindables.ui.views.XYToolbar
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric

open class XYFindablesUISampleActivity : XYBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_xyfindables_ui_sample)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val secondaryActivityButton = findViewById<XYButton>(R.id.secondaryActivityButton)
        secondaryActivityButton.setOnClickListener {
            val intent = Intent(this@XYFindablesUISampleActivity, XYSecondaryActivity::class.java)
            startActivity(intent)
        }
    }
}
