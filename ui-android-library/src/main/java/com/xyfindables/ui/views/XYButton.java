package com.xyfindables.ui.views;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Handler;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYGlobalFonts;


public class XYButton extends AppCompatButton {

    final private static String TAG = XYButton.class.getSimpleName();
    private OnClickListener _onClickListener;

    public XYButton(Context context) {
        this(context, null, 0);
    }

    public XYButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYButton(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextThemeWrapper(context, R.style.xy_button), attrs, defStyle);

        XYGlobalFonts.setViewFont(context, this);

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyBoard();
                if (_onClickListener != null) {
                    _onClickListener.onClick(v);
                }
            }
        });
    }

    @Override
    public void setOnClickListener(OnClickListener listener) {
        _onClickListener = listener;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    public void hideKeyBoard() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Activity activity = getActivity();
                if (activity != null) {
                    View view = activity.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
            }
        }, 50);
    }

    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

}
