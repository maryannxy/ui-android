package com.xyfindables.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.xyfindables.core.XYBase;
import com.xyfindables.ui.views.XYToolbar;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class XYBaseActivity extends AppCompatActivity {

    final private static String TAG = XYBaseActivity.class.getSimpleName();

    protected static final ThreadPoolExecutor _threadPool;

    static {
        _threadPool = new ThreadPoolExecutor(3, 30, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    private XYToolbar _toolbar;

    public XYToolbar getToolbar() {
        if (_toolbar == null) {
            _toolbar = (XYToolbar) findViewById(R.id.toolbar);
        }
        return _toolbar;
    }

    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(XYBaseActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadToolbar() {
        XYToolbar toolbar = getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            final ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Created: " + this.getLocalClassName());
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loadToolbar();
    }

    private BroadcastReceiver _connectivityReceiver;

    private void unregisterReceivers() {
        unregisterReceiver(_connectivityReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        XYBase.logAction(TAG, "onResume");
    }

    @Override
    public void onStart() {
        Log.i(TAG, "Started: " + this.getLocalClassName());
        super.onStart();
        XYBase.logAction(TAG, "onStart");
    }

    @Override
    public void onStop() {
        Log.i(TAG, "Stopped: " + this.getLocalClassName());
        super.onStop();
        XYBase.logAction(TAG, "onStop");
        unregisterReceivers();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "Destroyed: " + this.getLocalClassName());
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideKeyboard();
        XYBase.logAction(TAG, "onPause");
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

