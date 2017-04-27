package com.xyfindables.ui.views;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYBaseActivity;

/**
 * Created by arietrouw on 1/23/17.
 */

public class XYToolbar extends android.support.v7.widget.Toolbar {

    final private static String TAG = XYToolbar.class.getSimpleName();

    private boolean _backNavigationEnabled = false;

    public XYToolbar(Context context) {
        super(new ContextThemeWrapper(context, R.style.xy_toolbar));
        init(context);
    }

    public XYToolbar(Context context, AttributeSet attrs) {
        super(new ContextThemeWrapper(context, R.style.xy_toolbar), attrs);
        init(context);
    }

    public XYToolbar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        if (context instanceof XYBaseActivity) {
            XYBaseActivity activity = (XYBaseActivity)context;
            activity.setSupportActionBar(this);
            final ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(false);
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
            }
        }
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
