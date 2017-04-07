package com.xyfindables.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.xyfindables.core.XYBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

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
                int r = context.getResources().getIdentifier("fontawesome",
                        "raw", context.getPackageName());
                if (r != 0) {
                    InputStream ins = context.getResources().openRawResource(r);

                    File fontFile = null;

                    if (ins != null) {
                        try {
                            File outputDir = context.getCacheDir();
                            fontFile = File.createTempFile("font", "otf", outputDir);
                            int len = ins.available();
                            if (fontFile != null) {
                                byte[] fontBytes = new byte[len];
                                if (ins.read(fontBytes, 0, len) == len) {
                                    FileOutputStream fos = new FileOutputStream(fontFile);
                                    if (fos != null) {
                                        fos.write(fontBytes);
                                    }
                                }

                            }
                        } catch (IOException ex) {
                            logException(ex);
                        }
                    }
                    if (fontFile != null) {
                        _awesome[Typeface.NORMAL] = Typeface.createFromFile(fontFile);
                        for (int i = 1; i < 4; i++) {
                            _awesome[i] = Typeface.create(_awesome[Typeface.NORMAL], i);
                        }
                    }
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
                int r = context.getResources().getIdentifier("quicksand",
                        "raw", context.getPackageName());
                if (r != 0) {
                    InputStream ins = context.getResources().openRawResource(r);

                    File fontFile = null;

                    if (ins != null) {
                        try {
                            File outputDir = context.getCacheDir();
                            fontFile = File.createTempFile("font", "otf", outputDir);
                            int len = ins.available();
                            if (fontFile != null) {
                                byte[] fontBytes = new byte[len];
                                if (ins.read(fontBytes, 0, len) == len) {
                                    FileOutputStream fos = new FileOutputStream(fontFile);
                                    if (fos != null) {
                                        fos.write(fontBytes);
                                    }
                                }

                            }
                        } catch (IOException ex) {
                            logException(ex);
                        }
                    }
                    if (fontFile != null) {
                        _font[Typeface.NORMAL] = Typeface.createFromFile(fontFile);
                        for (int i = 1; i < 4; i++) {
                            _font[i] = Typeface.create(_font[Typeface.NORMAL], i);
                        }
                    }
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
