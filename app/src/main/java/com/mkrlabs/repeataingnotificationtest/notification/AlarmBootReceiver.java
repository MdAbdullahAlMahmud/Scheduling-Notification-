package com.mkrlabs.repeataingnotificationtest.notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmBootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            NotificationHelper.scheduleRepeatingElapsedNotification(context);
        }
    }
}