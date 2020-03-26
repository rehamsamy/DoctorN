package com.doctorn.notify;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

import com.doctorn.R;

import java.lang.annotation.Target;

public class OreoNotification extends ContextWrapper {
    public static  final  String channel_id="io";
    public static  final  String channel_name="ioo";
    NotificationManager manager;



    public OreoNotification(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O  ){
              createChannel();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel=new NotificationChannel(channel_id,channel_name,NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(false);
        channel.enableVibration(true);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel);

    }


    public NotificationManager getManager(){
        if(manager==null){
            manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }

        return  manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  Notification.Builder getNotification(String title, String body, PendingIntent pendingIntent, Uri uri, String icon){
        return  new Notification.Builder(getApplicationContext(),channel_id)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                .setSmallIcon(Integer.parseInt(icon))
                .setLargeIcon(largeIcon(getApplicationContext()))
                .setShowWhen(true)
                .setContentText(body)
                .setSound(uri)
                .setSmallIcon(Integer.parseInt(icon))
                .setAutoCancel(true);
    }


    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.doc);
        return largeIcon;
    }




}
