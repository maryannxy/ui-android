package com.xyfindables.ui;

/**
 * Created by arietrouw on 12/27/17.
 */

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

public class XYThrobberDialog extends Dialog {
    public XYThrobberDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_throbber);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        int dividerId = this.getContext().getResources().getIdentifier("android:id/titleDivider", null, null);
        View divider = this.findViewById(dividerId);
        if (divider != null) {
            divider.setVisibility(View.GONE);
        }
    }
}
