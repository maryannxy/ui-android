package com.xyfindables.ui.sample

import android.content.Intent
import android.os.Bundle

import com.xyfindables.ui.XYBaseActivity
import com.xyfindables.ui.views.XYButton

open class XYFindablesUISampleActivity : XYBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
