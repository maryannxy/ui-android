package com.xyfindables.ui.views;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYGlobalFonts;

/**
 * Created by arietrouw on 1/15/17.
 */

public class XYCheckBox extends AppCompatCheckBox {
    public XYCheckBox(Context context) {
        this(context, null, 0);
    }

    public XYCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextThemeWrapper(context, R.style.xy_checkbox), attrs, defStyle);
        XYGlobalFonts.setViewFont(context, this);
    }
}
