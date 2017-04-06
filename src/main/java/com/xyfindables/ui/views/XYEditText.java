package com.xyfindables.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.inputmethod.InputMethodManager;

import com.xyfindables.ui.R;
import com.xyfindables.ui.XYGlobalFonts;

/**
 * Created by arietrouw on 1/15/17.
 */

public class XYEditText extends AppCompatEditText {

    final private static String TAG = XYEditText.class.getSimpleName();
    private Paint _hintPaint;
    private Rect _hintRect = new Rect();
    private int _hintMargin = 0;
    private boolean _readOnly = false;

    public XYEditText(Context context) {
        this(context, null, 0);
    }

    public XYEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYEditText(Context context, AttributeSet attrs, int defStyle) {
        super(new ContextThemeWrapper(context, R.style.xy_edittext), attrs, defStyle);

        XYGlobalFonts.setViewFont(context, this);

        if (attrs != null) {
            TypedArray attributyeArray = getContext().obtainStyledAttributes(
                    attrs,
                    R.styleable.XYEditView,
                    0, 0);
            if (attributyeArray != null) {
                _readOnly = attributyeArray.getBoolean(R.styleable.XYEditView_readOnly, false);
                attributyeArray.recycle();
            }
        }

        _hintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        _hintPaint.setColor(getCurrentHintTextColor());
        _hintPaint.setTypeface(XYGlobalFonts.getFont(context));
        _hintPaint.setTextSize(convertDpToPixel(8, context));
        _hintPaint.setTextAlign(Paint.Align.LEFT);

        _hintMargin = (int) convertDpToPixel(4, context);

        updateFocusable();
        updatePadding();

    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return dp * (metrics.densityDpi / 160f);
    }

    private void updateFocusable() {
        setFocusable(isEnabled() && !isReadOnly());
        setFocusableInTouchMode(isEnabled() && ! isReadOnly());
        setSelectAllOnFocus(true);

    }

    private void updatePadding() {
        CharSequence hint = getHint();
        if (hint != null && hint.length() > 0) {
            int padding = (getPaddingTop() + getPaddingBottom());
            setPadding(getPaddingLeft(), padding * 75 / 100, getPaddingRight(), padding * 25 / 100);
        }
    }

    public void setReadOnly(boolean readOnly) {
        _readOnly = readOnly;
        updateFocusable();
    }

    public boolean isReadOnly() {
        return _readOnly;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        updateFocusable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        CharSequence hint = getHint();
        if (getText() != null && getText().length() > 0 && hint != null && hint.length() > 0) {
            _hintPaint.getTextBounds(hint.toString(), 0, hint.toString().length(), _hintRect);
            canvas.drawText(getHint().toString(), _hintMargin, _hintRect.height() + _hintMargin, _hintPaint);
        }
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        setCompoundDrawables(null, null, icon, null);
    }

    private Runnable _showImeRunnable = new Runnable() {
        public void run() {
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm != null) {
                imm.showSoftInput(XYEditText.this, 0);
            }
        }
    };

    public void setImeVisibility(final boolean visible) {
        if (visible) {
            postDelayed(_showImeRunnable, 100);
        } else {
            removeCallbacks(_showImeRunnable);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm != null) {
                imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
            }
        }
    }

    public void setAttention() {
        requestFocus();
        setImeVisibility(true);
        selectAll();
    }

}
