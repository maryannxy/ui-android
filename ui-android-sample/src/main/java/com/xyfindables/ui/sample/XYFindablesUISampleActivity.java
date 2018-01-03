package com.xyfindables.ui.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xyfindables.ui.XYBaseActivity;
import com.xyfindables.ui.views.XYButton;
import com.xyfindables.ui.views.XYToolbar;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class XYFindablesUISampleActivity extends XYBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_xyfindables_ui_sample);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        XYButton secondaryActivityButton = findViewById(R.id.secondaryActivityButton);
        secondaryActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XYFindablesUISampleActivity.this, XYSecondaryActivity.class);
                startActivity(intent);
            }
        });
    }
}
