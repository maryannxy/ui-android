package com.xyfindables.ui.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xyfindables.ui.views.XYToolbar;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class XYFindablesUISampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_xyfindables_ui_sample);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        XYToolbar toolbar = (XYToolbar) findViewById(R.id.toolbar);
        toolbar.enableBackNavigation(this);
        super.onPostCreate(savedInstanceState);
    }
}
