package com.xyfindables.ui.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.xyfindables.ui.R;

public class XYSignalBars extends View {

    final private static String TAG = XYSignalBars.class.getSimpleName();

    private int _barMaxCount = 3;
    private int _barCount = 0;

    private Paint paintStroke = null;

    private Paint paintEmpty = null;

    private Paint paintFill = null;

    final private static float _barWidthPercent = 0.70f;

    private RectF _rect = new RectF();

    public XYSignalBars(Context context) {
        this(context, null, 0);
    }

    public XYSignalBars(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XYSignalBars(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        int color = Color.WHITE;
        if (attrs != null) {
            int value = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "color", 0);
            if (value != 0) {
                color = getResources().getColor(value);
            } else {
                color = attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "color", Color.WHITE);
            }
        }

        if (attrs != null) {
            TypedArray attributyeArray = context.obtainStyledAttributes(
                    attrs,
                    R.styleable.XYSignalBars,
                    0, 0);

            if (attributyeArray != null) {
                _barMaxCount = attributyeArray.getInt(R.styleable.XYSignalBars_maxBars, 3);
                attributyeArray.recycle();
            }
        }

        paintStroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintStroke.setStyle(Style.STROKE);
        paintStroke.setStrokeWidth(2);

        paintEmpty = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintEmpty.setAlpha(96);
        paintEmpty.setStyle(Style.FILL);

        paintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintFill.setAlpha(192);
        paintFill.setStyle(Style.FILL);

        setColor(color);
    }

    public void setColor(int color) {
        paintStroke.setColor(color);
        paintStroke.setAlpha(255);
        paintEmpty.setColor(color);
        paintEmpty.setAlpha(96);
        paintFill.setColor(color);
        paintFill.setAlpha(192);
    }

    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        float width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        float height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();

        float barWidth = width / _barMaxCount;
        float barStep = height / (_barMaxCount);

        for (int i = 0; i < _barMaxCount; i++) {
            float left = (i * barWidth) + getPaddingLeft();
            float top = getPaddingTop() + (((_barMaxCount - 1) - i) * barStep);

            _rect.left = left + (barWidth * (1 - _barWidthPercent)) / 2;
            _rect.top = top;

            _rect.right = left + barWidth * _barWidthPercent + (barWidth * (1 - _barWidthPercent)) / 2;
            _rect.bottom = getMeasuredHeight() - getPaddingBottom();

            Paint currPaint = i < _barCount ? paintFill : paintEmpty;
            int radius = dpToPx(getResources(), 2);
            canvas.drawRoundRect(_rect, radius, radius, currPaint);
        }
    }

    public void setBarCount(int barCount) {
        if (barCount != _barCount) {
            _barCount = barCount;
            invalidate();
        }
    }

    public int dpToPx(@NonNull Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }

}
