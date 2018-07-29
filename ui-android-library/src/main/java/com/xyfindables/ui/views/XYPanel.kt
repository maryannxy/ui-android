package com.xyfindables.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

open class XYPanel (context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {
    var listener: Listener? = null

    open class Listener {
        open fun selected(panel: XYPanel?) {}
    }

    init {
        setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                listener?.selected(this@XYPanel)
            }
        })

        setOnLongClickListener(object : OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                listener?.selected(this@XYPanel)
                return true
            }
        })
    }
}