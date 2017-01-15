package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


/**
 * Created by Agnieszka on 14.01.2017.
 */
public class RingtonePlayingService extends Service {

    //private boolean isRunning;
    private Context context;
    MediaPlayer mMediaPlayer;
    private int startId;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        final NotificationManager mNM = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        String name = intent.getExtras().getString("name");

        mMediaPlayer = MediaPlayer.create(this, R.raw.one);
        mMediaPlayer.start();

        Intent intent1 = new Intent(this, Notify.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

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
