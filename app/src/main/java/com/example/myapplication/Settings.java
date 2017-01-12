package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends Activity {

    TextView settings;
    Button language;
    Button look;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);
        settings = (TextView) findViewById(R.id.Settings);
        language = (Button) findViewById(R.id.LanguageVer);
        look = (Button) findViewById(R.id.Look);
    }
}
