package com.xyfindables.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import com.xyfindables.ui.R;

/**
 * Created by arietrouw on 1/15/17.
 */

public class XYSpinner extends AppCompatSpinner {

    final private static String TAG = XYSpinner.class.getSimpleName();
    private String[] _entries = {};
    private String[] _values = {};
    private String _hint = null;

    public XYSpinner(final Context context) {
        this(context, null, 0);
    }

    public XYSpinner(final Context context, final AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.spinnerStyle);
    }

    public XYSpinner(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);

        if (attrs != null) {
            int idEntries = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "entries", 0);
            if (idEntries != 0) {
                _entries = context.getResources().getStringArray(idEntries);
            }

            TypedArray attributyeArray = getContext().obtainStyledAttributes(
                    attrs,
                    R.styleable.XYSpinner,
                    0, 0);

            if (attributyeArray != null) {

                int idValues = attributyeArray.getResourceId(R.styleable.XYSpinner_values, 0);
                if (idValues != 0) {
                    _values = context.getResources().getStringArray(idValues);
                }

                int hintId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "hint", 0);
                _hint = getResources().getString(hintId);
                attributyeArray.recycle();
            }
        }

        setAdapter(new SpinnerAdapter() {
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                XYTextView textView = new XYTextView(context);
                int padding = dpToPx(context.getResources(), 16);
                textView.setPadding(padding, padding, padding, padding);
                textView.setText(_entries[position]);
                return textView;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return _entries.length;
            }

            @Override
            public Object getItem(int position) {
                return _entries[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                XYEditText editText = new XYEditText(context);
                editText.setText(_entries[position]);
                editText.setReadOnly(true);
                editText.setHint(_hint);
                int padding = dpToPx(context.getResources(), 16);
                editText.setPadding(padding, padding, padding, padding);
                editText.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        XYSpinner.this.performClick();
                    }
                });
                return editText;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public boolean isEmpty() {
                return _entries.length == 0;
            }
        });

    }

    public int getValueIndex(String value) {
        for (int i = 0; i < _values.length; i++) {
            if (_values[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public boolean setSelection(String value) {
        int index = getValueIndex(value);
        if (index > -1) {
            setSelection(index);
            return true;
        } else {
            return false;
        }
    }

    public String getValue() {
        int index = getSelectedItemPosition();
        if (index >= _values.length) {
            return null;
        } else {
            return _values[getSelectedItemPosition()];
        }
    }

    public int dpToPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

}
