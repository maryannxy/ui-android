package com.xyfindables.ui.views;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYGlobalFonts;

public class XYTextView extends AppCompatTextView {
    public XYTextView(Context context) {
        this(context, null, 0);
    }

    public XYTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYTextView(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextThemeWrapper(context, R.style.xy_textview), attrs, defStyle);

        XYGlobalFonts.setViewFont(context, this);
    }
}
