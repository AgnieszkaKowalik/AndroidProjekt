package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String name = intent.getExtras().getString("name");
        Log.e("Nazwa leku", name);

        String meal = intent.getExtras().getString("meal");
        Log.e("meal", meal);

        Intent serviceIntent = new Intent(context,RingtonePlayingService.class);
        serviceIntent.putExtra("name", name);
        serviceIntent.putExtra("meal", meal);

        context.startService(serviceIntent);
    }


}

