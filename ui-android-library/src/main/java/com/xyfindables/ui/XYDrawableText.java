package com.xyfindables.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

public class XYDrawableText extends Drawable {
    private final String _text;
    private final float _size;
    @NonNull
    private final Paint _paint;

    public XYDrawableText(String text, int color, float size, Typeface typeFace) {

        _text = text;
        _size = size;

        _paint = new Paint();
        _paint.setTypeface(typeFace);
        _paint.setColor(color);
        _paint.setTextSize(size);
        _paint.setAntiAlias(true);
        _paint.setFakeBoldText(true);
        _paint.setStyle(Paint.Style.FILL);
        _paint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawText(_text, 0, _size, _paint);
    }

    @Override
    public void setAlpha(int alpha) {
        _paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        _paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}
