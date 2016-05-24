package com.iptv.tvwidget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

public class RadioButtonView extends RadioButton {

    public RadioButtonView(Context context) {
        super(context);

    }

    public RadioButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RadioButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDrawableTopSize(int width, int height) {
        Drawable[] awb = getCompoundDrawables();
        Drawable top = awb[1];
        Bitmap newBm = getNewBm(top, width, height);
        if (newBm != null) {
            Drawable result = new BitmapDrawable(newBm);
            result.setBounds(0, 0, newBm.getWidth(), newBm.getHeight());
            setCompoundDrawables(awb[0], result, awb[2], awb[3]);
        }

    }

    @SuppressWarnings("deprecation")
    private Bitmap getNewBm(Drawable top, int width, int height) {
        if (top != null) {
            if (top instanceof BitmapDrawable) {
                Bitmap bm = ((BitmapDrawable) top).getBitmap();
                Bitmap result = Bitmap.createBitmap(width, height,
                        Config.ARGB_8888);
                Canvas canvas = new Canvas(result);
                Paint paint = new Paint();
                paint.setDither(false);
                paint.setFilterBitmap(true);
                Rect src = new Rect(0, 0, bm.getWidth(), bm.getHeight());
                Rect dst = new Rect(0, 0, width, height);
                canvas.drawBitmap(bm, src, dst, paint);
                Log.i("rb",
                        "w=" + result.getWidth() + ",h=" + result.getHeight());
                return result;
            }

        }
        return null;
    }

    public void setDrawablTopScale(float scale) {
        Drawable[] awb = getCompoundDrawables();
        Drawable top = awb[1];
        if (top != null) {
            if (top instanceof BitmapDrawable) {
                Bitmap bm = ((BitmapDrawable) top).getBitmap();
                setDrawableTopSize((int) (bm.getWidth() * scale),
                        (int) (bm.getHeight() * scale));
            }
        }
    }


}
