package com.xyfindables.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by arietrouw on 1/15/17.
 */

public class XYSmallPanelView extends LinearLayout {

    private static int _instanceCount = 0;

    public XYSmallPanelView(Context context) {
        this(context, null, 0);
    }

    public XYSmallPanelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYSmallPanelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        _instanceCount++;
    }

    @Override
    protected void finalize() {
        _instanceCount--;
        //noinspection EmptyCatchBlock
        try {
            super.finalize();
        } catch (Throwable ex) {
        }
    }

    public static int getInstanceCount() {
        return _instanceCount;
    }
}
