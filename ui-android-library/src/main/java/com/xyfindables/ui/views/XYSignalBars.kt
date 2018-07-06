package com.xyfindables.ui.views

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.AlphaAnimation

import com.xyfindables.ui.R

class XYSignalBars @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {

    private var _barMaxCount = 3
    private var _barCount = 0

    private var paintStroke: Paint? = null

    private var paintEmpty: Paint? = null

    private var paintFill: Paint? = null

    private val _rect = RectF()

    init {

        var color = Color.WHITE
        if (attrs != null) {
            val value = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "color", 0)
            if (value != 0) {
                color = @Suppress("DEPRECATION")resources.getColor(value)
            } else {
                color = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "color", Color.WHITE)
            }
        }

        if (attrs != null) {
            val attributyeArray = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.XYSignalBars,
                    0, 0)

            if (attributyeArray != null) {
                _barMaxCount = attributyeArray.getInt(R.styleable.XYSignalBars_maxBars, 3)
                attributyeArray.recycle()
            }
        }

        paintStroke = Paint(Paint.ANTI_ALIAS_FLAG)
        paintStroke!!.style = Style.STROKE
        paintStroke!!.strokeWidth = 2f

        paintEmpty = Paint(Paint.ANTI_ALIAS_FLAG)
        paintEmpty!!.alpha = 96
        paintEmpty!!.style = Style.FILL

        paintFill = Paint(Paint.ANTI_ALIAS_FLAG)
        paintFill!!.alpha = 192
        paintFill!!.style = Style.FILL

        setColor(color)
    }

    fun setColor(color: Int) {
        paintStroke!!.color = color
        paintStroke!!.alpha = 255
        paintEmpty!!.color = color
        paintEmpty!!.alpha = 96
        paintFill!!.color = color
        paintFill!!.alpha = 192
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = (measuredWidth - paddingLeft - paddingRight).toFloat()
        val height = (measuredHeight - paddingTop - paddingBottom).toFloat()

        val barWidth = width / _barMaxCount
        val barStep = height / _barMaxCount

        for (i in 0 until _barMaxCount) {
            val left = i * barWidth + paddingLeft
            val top = paddingTop + (_barMaxCount - 1 - i) * barStep

            _rect.left = left + barWidth * (1 - _barWidthPercent) / 2
            _rect.top = top

            _rect.right = left + barWidth * _barWidthPercent + barWidth * (1 - _barWidthPercent) / 2
            _rect.bottom = (measuredHeight - paddingBottom).toFloat()

            val currPaint = if (i < _barCount) paintFill else paintEmpty
            val radius = dpToPx(resources, 2)
            canvas.drawRoundRect(_rect, radius.toFloat(), radius.toFloat(), currPaint)
        }
    }

    fun setBarCount(barCount: Int, animate:Boolean) {
        post{
            if (barCount != _barCount) {
                _barCount = barCount
                invalidate()
            }
            if (animate) {
                val animation = AlphaAnimation(0.0f, 1.0f)
                animation.setDuration(500)
                this.startAnimation(animation)
            }
        }
    }

    fun dpToPx(res: Resources, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), res.displayMetrics).toInt()
    }

    companion object {

        private val TAG = XYSignalBars::class.java.simpleName

        private val _barWidthPercent = 0.70f
    }

}
