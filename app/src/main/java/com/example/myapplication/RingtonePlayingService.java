package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;


/**
 * Created by Agnieszka on 14.01.2017.
 */
public class RingtonePlayingService extends Service {

    //private boolean isRunning;
    MediaPlayer mMediaPlayer;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String name = intent.getExtras().getString("name");
        String meal = intent.getExtras().getString("meal");

        mMediaPlayer = MediaPlayer.create(this, R.raw.one);
        mMediaPlayer.start();

        Intent notifyIntent = new Intent(this, Notify.class);
        notifyIntent.putExtra("name", name);
        notifyIntent.putExtra("meal", meal);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        Notification mNotify  = new Notification.Builder(this)
                .setContentTitle("Czas na lek" + "!")
                .setContentText(name)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .build();

        mNM.notify(1, mNotify);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.e("RingtoneService", "on destroy called");
        mMediaPlayer.stop();
        super.onDestroy();
    }
}
