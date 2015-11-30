package com.androidbelieve.footballlivescore.favorite_match;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.androidbelieve.footballlivescore.R;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/30/15.
 */
public class NotificationMessage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        if (arg1 != null && arg1.hasExtra("MATCH_TITLE")) {
            String title = arg1.getStringExtra("MATCH_TITLE");
            showNotification(context, title);
            ring(context);
        } else {
            showNotification(context, "Match Start!!!");
            ring(context);
        }
    }

    private void showNotification(Context context, String title) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, NotificationMessage.class), 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_chelsea)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.ic_launcher))
                        .setContentTitle("FootBall match will start after 5 mins!!!")
                        .setContentText(title);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
        //Ring tone
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void ring(Context context) {
        Log.d("Vvvv", " ringgg");
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer mp = MediaPlayer.create(context, notification);
        mp.start();
    }
}
