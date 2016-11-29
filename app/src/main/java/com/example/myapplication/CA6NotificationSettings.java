package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class CA6NotificationSettings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_ca6_notification_settings);
    }

    @Override
    public void onBackPressed(){  //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do CA3Hello
        Intent intent = new Intent(CA6NotificationSettings.this, CA3Hello.class);
        startActivity(intent);
    }


}
