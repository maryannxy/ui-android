package com.xyfindables.ui.views

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.database.DataSetObserver
import android.support.v7.widget.AppCompatSpinner
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.SpinnerAdapter

import com.xyfindables.ui.R

/**
 * Created by arietrouw on 1/15/17.
 */

class XYSpinner @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyle: Int = android.support.v7.appcompat.R.attr.spinnerStyle) : AppCompatSpinner(context, attrs, defStyle) {
    private var _entries = arrayOf<String?>()
    private var _values = arrayOf<String?>()
    private var _hint: String? = null

    val value: String?
        get() {
            val index = selectedItemPosition
            return if (index >= _values.size) {
                null
            } else {
                _values[selectedItemPosition]
            }
        }

    constructor(context: Context) : this(context, null, 0) {}

    init {

        if (attrs != null) {
            val idEntries = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "entries", 0)
            if (idEntries != 0) {
                _entries = context.resources.getStringArray(idEntries)
            }

            val attributyeArray = getContext().obtainStyledAttributes(
                    attrs,
                    R.styleable.XYSpinner,
                    0, 0)

            if (attributyeArray != null) {

                val idValues = attributyeArray.getResourceId(R.styleable.XYSpinner_values, 0)
                if (idValues != 0) {
                    _values = context.resources.getStringArray(idValues)
                }

                val hintId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "hint", 0)
                _hint = resources.getString(hintId)
                attributyeArray.recycle()
            }
        }

        adapter = object : SpinnerAdapter {
            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val textView = XYTextView(context)
                val padding = dpToPx(context.resources, 16)
                textView.setPadding(padding, padding, padding, padding)
                textView.text = _entries[position]
                return textView
            }

            override fun registerDataSetObserver(observer: DataSetObserver) {

            }

            override fun unregisterDataSetObserver(observer: DataSetObserver) {

            }

            override fun getCount(): Int {
                return _entries.size
            }

            override fun getItem(position: Int): Any {
                return _entries[position]!!
            }

            override fun getItemId(position: Int): Long {
                return position.toLong()
            }

            override fun hasStableIds(): Boolean {
                return false
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val editText = XYEditText(context)
                editText.setText(_entries[position])
                editText.isReadOnly = true
                editText.hint = _hint
                val padding = dpToPx(context.resources, 16)
                editText.setPadding(padding, padding, padding, padding)
                editText.setOnClickListener { this@XYSpinner.performClick() }
                return editText
            }

            override fun getItemViewType(position: Int): Int {
                return 0
            }

            override fun getViewTypeCount(): Int {
                return 1
            }

            override fun isEmpty(): Boolean {
                return _entries.size == 0
            }
        }

    }

    fun getValueIndex(value: String): Int {
        for (i in _values.indices) {
            if (_values[i] == value) {
                return i
            }
        }
        return -1
    }

    fun setSelection(value: String): Boolean {
        val index = getValueIndex(value)
        if (index > -1) {
            setSelection(index)
            return true
        } else {
            return false
        }
    }

    fun dpToPx(res: Resources, dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), res.displayMetrics).toInt()
    }

    fun setEntries(entries: Array<String?>) {
        _entries = entries
    }

    fun setValues(values: Array<String?>) {
        _values = values
    }

    companion object {

        private val TAG = XYSpinner::class.java.simpleName
    }
}
