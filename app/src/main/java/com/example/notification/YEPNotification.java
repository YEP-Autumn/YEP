package com.example.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.lapace.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class YEPNotification {

    private static final String TAG = "YEP";
    private Context context;
    private PendingIntent pendingIntent;
    private String id = "取消";
    private String title = "通知 By YEP";
    private String text = "您有一条新的消息请注意查收！";
    private String bigText;
    private int smallResource = R.drawable.white_icon;
    private Bitmap bitmapResource;

    public YEPNotification(Context context) {
        this.context = context;
    }
    private volatile int count = 0;
    //开启华为角标显示
    boolean mIsSupportedBade = true;

    public void startN(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, id);
        if(bigText!=null)builder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
        if(pendingIntent!=null)builder.setContentIntent(pendingIntent);
        if(bitmapResource!=null){
            builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmapResource).bigLargeIcon(null))
                    .setLargeIcon(bitmapResource);
        }
        builder.setSmallIcon(smallResource)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.DEFAULT_ALL);

        NotificationManager manager = context.getSystemService(NotificationManager.class);
        NotificationChannel channel  = new NotificationChannel(id,"消息通知",NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(channel);
        NotificationManagerCompat from = NotificationManagerCompat.from(context);

//        //华为角标设置
//        if (mIsSupportedBade) {
//            setBadgeNum(count);
//        }
        from.notify(count++,builder.build());
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.pendingIntent = pendingIntent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setSmallResource(int smallResource) {
        this.smallResource = smallResource;
    }

    public void setBitmapResource(Bitmap bitmapResource) {
        this.bitmapResource = bitmapResource;
    }
    public void setBigText(String bigText) {
        this.bigText = bigText;
    }


    public void setBadgeNum(int num) {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package", context.getPackageName()); // com.test.badge is your package name
            String launchClassName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
            bundle.putString("class", launchClassName); // com.test. badge.MainActivity is your apk main activity
            bundle.putInt("badgenumber", num);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
        } catch (Exception e) {
            mIsSupportedBade = false;
        }
    }
}
