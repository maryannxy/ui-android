package com.xyfindables.ui.views

import android.content.Context
import android.support.v7.widget.AppCompatCheckBox
import android.util.AttributeSet
import android.view.ContextThemeWrapper

import com.xyfindables.ui.R
import com.xyfindables.ui.XYGlobalFonts

/**
 * Created by arietrouw on 1/15/17.
 */

class XYCheckBox @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : AppCompatCheckBox(ContextThemeWrapper(context, R.style.xy_checkbox), attrs, defStyle) {

    init {
        XYGlobalFonts.setViewFont(context, this)
    }
}
