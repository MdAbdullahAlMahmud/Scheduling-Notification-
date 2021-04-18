package com.mkrlabs.repeataingnotificationtest.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mkrlabs.repeataingnotificationtest.R;

import java.util.concurrent.TimeUnit;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.ALARM_SERVICE;

public class DeviceRestartReceiver extends BroadcastReceiver {
    //@Munna--18 April 2021
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {

            //--------- These line of code to display notification after device restart------------//
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notify")
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                    .setContentTitle("Remainder")
                    .setContentText("Hey , Your device is restarted")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(200, builder.build());
            //---------

            // As when device got restart it destroy all alarm so  we have recreate that alarm
            Intent notificationIntent = new Intent(context, MyNotificationPublisher.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,notificationIntent,0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            long timestamp = System.currentTimeMillis();
            long afterTenSecond= 1000*10;
            int myMinutes = 2;
            long timeInMilliSeconds= TimeUnit.MINUTES.toMillis(myMinutes);
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,timestamp+afterTenSecond,timeInMilliSeconds,pendingIntent);
            // As when device got restart it destroy all alarm so  we have recreate that alarm


        }
    }
}
