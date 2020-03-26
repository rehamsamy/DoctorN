package com.doctorn.notify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.doctorn.R;
import com.doctorn.voiceChat.VoiceChatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.nio.Buffer;
import java.util.Date;

public class FirebaseMessage extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       String sented= remoteMessage.getData().get("sented");

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
//        if(user!=null && sented.equals(user.getUid())){
//
//        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            sendOreoNotification(remoteMessage);

        }else {
            sendNotification(remoteMessage);
        }
        Log.v("TAG","sindddd"+sented);




    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendOreoNotification(RemoteMessage remoteMessage) {

        String user=remoteMessage.getData().get("user");
        String body=remoteMessage.getData().get("body");
        String title=remoteMessage.getData().get("title");
        String icon=remoteMessage.getData().get("icon");

        RemoteMessage.Notification notification=remoteMessage.getNotification();
        int j= Integer.parseInt(user.replaceAll("[\\D]",""));

        Intent intent=new Intent(this, VoiceChatActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("userId",user);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,j,intent,PendingIntent.FLAG_NO_CREATE);
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        OreoNotification oreoNotification=new OreoNotification(this);
        Notification.Builder builder=oreoNotification.getNotification(title,body,pendingIntent,sound,icon);


        int i=0;
        if(j>0){
            i=j;
        }

        oreoNotification.getManager().notify(i,builder.build());




    }

    private void sendNotification(RemoteMessage remoteMessage) {
        String user=remoteMessage.getData().get("user");
        String body=remoteMessage.getData().get("body");
        String title=remoteMessage.getData().get("title");
        String icon=remoteMessage.getData().get("icon");

        RemoteMessage.Notification notification=remoteMessage.getNotification();
        int j= Integer.parseInt(user.replaceAll("[\\D]",""));

        Intent intent=new Intent(this, VoiceChatActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("userId",user);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,j,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary))
                .setSmallIcon(Integer.parseInt(icon))
                .setLargeIcon(largeIcon(getApplicationContext()))
                .setShowWhen(true)
                .setContentText(title)
                .setContentText(body)
                .setSound(sound)
                .setContentIntent(pendingIntent);

        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int i=0;
        if(j>0){
            i=j;
        }

       manager.notify(i,builder.build());


    }


    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.doc);
        return largeIcon;
    }



}
