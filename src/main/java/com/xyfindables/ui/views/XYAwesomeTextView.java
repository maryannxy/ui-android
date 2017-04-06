package com.xyfindables.ui.views;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYGlobalFonts;

/**
 * Created by arietrouw on 1/15/17.
 */

public class XYAwesomeTextView extends XYTextView {
    public XYAwesomeTextView(Context context) {
        this(context, null, 0);
    }

    public XYAwesomeTextView(Context context, @NonNull AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYAwesomeTextView(Context context, @NonNull AttributeSet attrs, int defStyle) {
        super(new ContextThemeWrapper(context, R.style.xy_awesome_textview), attrs, defStyle);
        XYGlobalFonts.setViewFontAwesome(context, this);
    }
}
