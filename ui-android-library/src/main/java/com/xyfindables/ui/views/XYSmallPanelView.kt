package com.xyfindables.ui.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

/**
 * Created by arietrouw on 1/15/17.
 */

open class XYSmallPanelView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {

    init {
        instanceCount++
    }

    protected fun finalize() {
        instanceCount--
    }

    companion object {

        var instanceCount = 0
            private set
    }
}
