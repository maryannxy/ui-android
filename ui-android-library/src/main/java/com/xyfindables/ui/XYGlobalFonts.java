package com.xyfindables.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.xyfindables.core.XYBase;

/**
 * Created by arietrouw on 1/15/17.
 */

public class XYGlobalFonts extends XYBase {
    private static Typeface[] _font;
    private static Typeface[] _awesome;

    public static Typeface getFontAwesome(Context context) {
        return getFontAwesome(context, Typeface.NORMAL);
    }

    public static Typeface getFontAwesome(Context context, int style) {
        synchronized (XYGlobalFonts.class) {
            if (_awesome == null) {
                _awesome = new Typeface[4];
                try {
                    _awesome[Typeface.NORMAL] = Typeface.createFromAsset(context.getAssets(), "fonts/FontAwesome.otf");
                } catch (Exception ex) {
                    _awesome[Typeface.NORMAL] = Typeface.DEFAULT;
                }
                for (int i = 1; i < 4; i++) {
                    _awesome[i] = Typeface.create(_awesome[Typeface.NORMAL], i);
                }
            }
        }
        return _awesome[style];
    }

    public static Typeface getFont(Context context) {
        return getFont(context, Typeface.NORMAL);
    }

    public static Typeface getFont(Context context, int style) {
        synchronized (XYGlobalFonts.class) {
            if (_font == null) {
                _font = new Typeface[4];
                try {
                    _font[Typeface.NORMAL] = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand.otf");
                } catch (Exception ex) {
                    _font[Typeface.NORMAL] = Typeface.DEFAULT;
                }
                for (int i = 1; i < 4; i++) {
                    _font[i] = Typeface.create(_font[Typeface.NORMAL], i);
                }
            }
        }
        return _font[style];
    }

    public static void setViewFont(Context context, TextView view) {
        Typeface typeFace = view.getTypeface();
        if (typeFace != null) {
            view.setTypeface(getFont(context, typeFace.getStyle()));
        } else {
            view.setTypeface(getFont(context));
        }
    }

    public static void setPreferenceFont(Context context, View view) {
        TextView titleView = (TextView)view.findViewById(android.R.id.title);
        if (titleView != null) {
            XYGlobalFonts.setViewFont(context, titleView);
        }

        TextView summaryView = (TextView)view.findViewById(android.R.id.summary);
        if (summaryView != null) {
            XYGlobalFonts.setViewFont(context, summaryView);
        }
    }

    public static void setViewFontAwesome(Context context, TextView view) {
        Typeface typeFace = view.getTypeface();
        if (typeFace != null) {
            view.setTypeface(getFontAwesome(context, typeFace.getStyle()));
        } else {
            view.setTypeface(getFontAwesome(context));
        }
    }

    public static XYDrawableText getFontAwesomeDrawable(Context context, int text, int color, float size) {
        return getFontAwesomeDrawable(context, text, color, size, Typeface.NORMAL);
    }

    public static XYDrawableText getFontAwesomeDrawable(Context context, int text, int color, float size, int style) {
        return new XYDrawableText(context.getResources().getString(text), context.getResources().getColor(color), size, getFontAwesome(context, style));
    }
}
