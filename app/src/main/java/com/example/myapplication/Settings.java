package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void onBackPressed(){   //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do MainActivity
        Intent intent = new Intent(Settings.this, MainActivity.class);
        startActivity(intent);
    }

}
