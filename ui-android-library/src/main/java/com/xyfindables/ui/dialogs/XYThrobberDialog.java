package com.xyfindables.ui.dialogs;

/*
 Created by arietrouw on 12/27/17.
 */

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYBaseActivity;

import static com.xyfindables.core.XYBase.logInfo;

public class XYThrobberDialog extends Dialog {

    final private static String TAG = XYThrobberDialog.class.getSimpleName();

    private Activity _activity;

    public XYThrobberDialog(Activity activity) {
        super(activity);
        _activity = activity;
        setContentView(R.layout.dialog_throbber);
        Window window = this.getWindow();
        if (window != null) {
            this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            int dividerId = this.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
            View divider = this.findViewById(dividerId);
            if (divider != null) {
                divider.setVisibility(View.GONE);
            }
        }
    }

    public void show() {
        logInfo(TAG, "showThrobber");
        XYBaseActivity.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                if (_activity.isFinishing()) {
                    XYThrobberDialog.super.dismiss();
                } else {
                    XYThrobberDialog.super.show();
                }
            }
        });
    }

    public void hide() {
        logInfo(TAG, "showThrobber");
        XYBaseActivity.RunOnUIThread(new Runnable() {
            @Override
            public void run() {
                XYThrobberDialog.super.dismiss();
            }
        });
    }
}
