package com.mkrlabs.repeataingnotificationtest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mkrlabs.repeataingnotificationtest.services.MyNotificationPublisher;
import com.mkrlabs.repeataingnotificationtest.R;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    Button notifyButton,cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        notifyButton=findViewById(R.id.notifyButton);
        cancelButton=findViewById(R.id.cancelButton);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyNotificationPublisher.class);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                if (alarmManager!=null){
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                    alarmManager.cancel(pendingIntent);
                }
            }
        });
    }

    public  void setAlarm(){
        Intent intent = new Intent(MainActivity.this,MyNotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        long timestamp = System.currentTimeMillis();
        long afterTenSecond= 1000*10;
        int myMinutes = 2;
        long timeInMilliSeconds= TimeUnit.MINUTES.toMillis(myMinutes);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,timestamp+afterTenSecond,timeInMilliSeconds,pendingIntent);

    }
    private void createNotificationChannel () {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = "Channel";
            String description = "Channel of Water Reminder";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notify",name,importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}