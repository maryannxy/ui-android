package com.xyfindables.ui.views;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.xyfindables.ui.R;

/**
 * Created by arietrouw on 1/23/17.
 */

public class XYToolbar extends android.support.v7.widget.Toolbar {

    final private static String TAG = XYToolbar.class.getSimpleName();

    private boolean _backNavigationEnabled = false;

    public XYToolbar(Context context) {
        this(context, null, 0);
    }

    public XYToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void enableMenuNavigation(OnClickListener onClickListener) {
        _backNavigationEnabled = false;
        setNavigationIcon(getResources().getDrawable(R.drawable.xy_ui_toolbar_menu));
        setNavigationOnClickListener(onClickListener);
    }

    public void enableBackNavigation(final Activity activity) {
        _backNavigationEnabled = true;
        setNavigationIcon(getResources().getDrawable(R.drawable.xy_ui_toolbar_back));
        setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public void enableBackNavigation(OnClickListener onClickListener) {
        _backNavigationEnabled = true;
        setNavigationIcon(getResources().getDrawable(R.drawable.xy_ui_toolbar_back));
        setNavigationOnClickListener(onClickListener);
    }

    public boolean isBackNavigationEnabled() {
        return _backNavigationEnabled;
    }
}
