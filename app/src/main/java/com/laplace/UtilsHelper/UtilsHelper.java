package com.laplace.UtilsHelper;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;

import java.util.concurrent.atomic.AtomicBoolean;

public class UtilsHelper extends FieldStatic {
    /**
     * 截取 view
     *
     * @param view
     * @return
     */
    public static Bitmap screen(View view) {
        view.buildLayer();
        Bitmap bitmap = Bitmap.createBitmap(view.getHeight(), view.getWidth(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate((float) -view.getScrollX(), (float) -view.getScrollY());
        view.draw(canvas);
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        return bitmap;
    }

    /**
     * 截取 window
     *
     * @param window
     * @return
     */
    public Bitmap screen(Window window) {
        AtomicBoolean done = new AtomicBoolean(false);
        Bitmap bitmap = Bitmap.createBitmap(window.getDecorView().getWidth(), window.getDecorView().getHeight(), Bitmap.Config.ARGB_8888);
        Handler handler = new Handler(Looper.getMainLooper());
        Object locker = new Object();
        PixelCopy.request(window, bitmap, (code) -> {
            if (PixelCopy.SUCCESS != code) {
                return;
            }
            done.set(true);
            synchronized (locker) {
                locker.notifyAll();
            }
        }, handler);
        while (!done.get()) {
            synchronized (locker) {
                try {
                    locker.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.yield();
        }
        Log.e(TAG, bitmap.getWidth() + "x" + bitmap.getHeight());
        return bitmap;
    }
}
