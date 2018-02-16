package com.xyfindables.ui.sample;

import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.xyfindables.ui.XYBaseActivity;
import com.xyfindables.ui.views.XYToolbar;

import io.fabric.sdk.android.Fabric;

/**
 * Created by arietrouw on 12/29/17.
 */

public class XYSecondaryActivity extends XYBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        toolbar().enableBackNavigation(this);
        super.onPostCreate(savedInstanceState);
    }
}
