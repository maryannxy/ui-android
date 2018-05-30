package com.xyfindables.ui.views

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.ContextThemeWrapper

import com.xyfindables.ui.R
import com.xyfindables.ui.XYGlobalFonts

open class XYTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : AppCompatTextView(ContextThemeWrapper(context, R.style.xy_textview), attrs, defStyle) {

    init {

        XYGlobalFonts.setViewFont(context, this)
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }
}
