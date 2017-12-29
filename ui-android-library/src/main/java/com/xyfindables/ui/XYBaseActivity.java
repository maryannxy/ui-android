package com.xyfindables.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.xyfindables.ui.dialogs.XYSplashDialog;
import com.xyfindables.ui.dialogs.XYThrobberDialog;
import com.xyfindables.ui.views.XYToolbar;
import com.xyfindables.core.XYBase;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.Fabric;

import static com.xyfindables.core.XYBase.*;

public abstract class XYBaseActivity extends AppCompatActivity {

    final private static String TAG = XYBaseActivity.class.getSimpleName();

    public static void RunOnUIThread(Runnable action) {
        if( Looper.myLooper() == Looper.getMainLooper() ) {
            action.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(action);
        }
    }

    protected static final ThreadPoolExecutor _threadPool;

    static {
        _threadPool = new ThreadPoolExecutor(3, 30, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    public XYToolbar _toolbar;
    public XYToolbar toolbar() {
        if (_toolbar == null) {
            _toolbar = findViewById(R.id.toolbar);
        }
        return _toolbar;
    }

    public XYThrobberDialog throbber;

    protected static int _activityCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        logStatus(TAG, "Activity Created: " + this.getLocalClassName());
        Fabric.with(this, new Answers(), new Crashlytics());
        throbber = new XYThrobberDialog(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        XYBase.logStatus(TAG, "Activity Resumed: " + this.getLocalClassName());
        super.onResume();
        _activityCount++;
        logInfo(TAG, "onResume:" + _activityCount + ":" + this.getLocalClassName());
    }

    @Override
    public void onStart() {
        XYBase.logStatus(TAG, "Activity Started: " + this.getLocalClassName());
        super.onStart();
    }

    @Override
    public void onStop() {
        XYBase.logStatus(TAG, "Activity Stopped: " + this.getLocalClassName());
        super.onStop();
        _activityCount--;
    }

    @Override
    protected void onDestroy() {
        XYBase.logStatus(TAG, "Activity Destroyed: " + this.getLocalClassName());
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        XYBase.logStatus(TAG, "Activity Paused: " + this.getLocalClassName());
        super.onPause();
        throbber.hide();
        hideKeyboard();
    }

    public static boolean isForeground() {
        return (_activityCount > 0);
    }

    private ProgressDialog _progressBar = null;

    protected void showProgressBar() {
        logInfo(TAG, "showProgressBar");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (_progressBar != null && !isFinishing()) {
                    _progressBar.show();
                }
            }
        });
    }

    protected void showProgressBar(final String title, final String message, final boolean cancellable, final int max) {
        logInfo(TAG, "showProgressBar");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (_progressBar == null) {
                    _progressBar = new ProgressDialog(XYBaseActivity.this);
                    _progressBar.setTitle(title);
                    _progressBar.setMessage(message);
                    _progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    _progressBar.setCancelable(cancellable);
                    _progressBar.setMax(max);
                    if (!isFinishing()) {
                        _progressBar.show();
                    }
                }
            }
        });
    }

    protected void incrementProgressBar(final int increment) {
        if (_progressBar != null) {
            _progressBar.incrementProgressBy(increment);
        }
    }

    protected ProgressDialog getProgressBar() {
        return _progressBar;
    }

    protected void hideProgressBar() {
        logInfo(TAG, "hideProgressBar");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (_progressBar != null && _progressBar.isShowing()) {
                    _progressBar.dismiss();
                    _progressBar = null;
                }
            }
        });
    }

    protected void setProgressBarMessage(final String message) {
        logInfo(TAG, "setProgressBarMessage");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (_progressBar != null) {
                    _progressBar.setMessage(message);
                }
            }
        });
    }

    private XYSplashDialog _dialogSplash = null;

    protected void showSplash() {
        logInfo(TAG, "showSplash");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (_dialogSplash == null) {
                    _dialogSplash = new XYSplashDialog(XYBaseActivity.this);
                    if (!isFinishing()) {
                        _dialogSplash.show();
                    }
                }
            }
        });
    }

    protected void hideSplash() {
        logInfo(TAG, "hideSplash");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (_dialogSplash != null) {
                    _dialogSplash.dismiss();
                    _dialogSplash = null;
                }
            }
        });
    }

    public void showToast(final String message) {
        logInfo(TAG, "showProgressBar");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(XYBaseActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void hideKeyboard() {
        logInfo(TAG, "hideKeyboard");
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

