package com.mkrlabs.repeataingnotificationtest.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mkrlabs.repeataingnotificationtest.R;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyNotificationPublisher extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification-id" ;
    public static String NOTIFICATION = "notification" ;
    @Override
    public void onReceive (Context context , Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"notify")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Water Reminder")
                .setContentText("Hey , lets have a glass of water ")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                ;
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200,builder.build());
    }
}
