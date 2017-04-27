package com.xyfindables.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYGlobalFonts;

public class XYAwesomeButton extends XYButton {
    public XYAwesomeButton(Context context) {
        this(context, null, 0);
    }

    public XYAwesomeButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYAwesomeButton(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextThemeWrapper(context, R.style.xy_awesome_button), attrs, defStyle);
        XYGlobalFonts.setViewFontAwesome(context, this);
    }
}
