package com.xyfindables.ui.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

import com.xyfindables.ui.XYBaseActivity;

import static com.xyfindables.core.XYBase.logInfo;

public class XYProgressDialog extends ProgressDialog {
    final private static String TAG = XYProgressDialog.class.getSimpleName();

    public XYProgressDialog(Context context) {
        super(context);
    }

    public void show(final String title, final String message, final boolean cancellable, final int max) {
        logInfo(TAG, "show");
        XYBaseActivity.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                    setTitle(title);
                    setMessage(message);
                    setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    setCancelable(cancellable);
                    setMax(max);
                    show();
                }
            }
        );
    }

    public void hide() {
        logInfo(TAG, "hide");
        XYBaseActivity.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (isShowing()) {
                    dismiss();
                }
            }
        });
    }

    public void show() {
        logInfo(TAG, "hide");
        XYBaseActivity.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (!isShowing()) {
                    show();
                }
            }
        });
    }

    protected void setMessage(final String message) {
        logInfo(TAG, "setMessage");
        XYBaseActivity.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                    setMessage(message);
            }
        });
    }
}
