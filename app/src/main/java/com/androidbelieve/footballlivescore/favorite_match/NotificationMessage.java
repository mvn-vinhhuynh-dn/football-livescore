package com.androidbelieve.footballlivescore.favorite_match;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.androidbelieve.footballlivescore.R;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by VinhHlb on 11/30/15.
 */
public class NotificationMessage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {
        if (arg1 != null && arg1.hasExtra(FavoriteFragment.MATCH_TITLE)) {
            String title = arg1.getStringExtra(FavoriteFragment.MATCH_TITLE);
            showNotification(context, title);
        } else {
            showNotification(context, "Match Start!!!");
        }
    }

    private void showNotification(Context context, String title) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, NotificationMessage.class), 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                R.drawable.ic_launcher))
                        .setContentTitle("FootBall match will start after 5 mins!!!")
                        .setContentText(title);
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
