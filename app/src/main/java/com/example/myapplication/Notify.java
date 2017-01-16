package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Agnieszka on 19.12.2016.
 */
public class Notify extends Activity {

    TextView nazwa_leku;
    TextView posilek_leku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notification);

        nazwa_leku = (TextView) findViewById(R.id.nazwa_leku);
        posilek_leku = (TextView) findViewById(R.id.posilek);
        Button potwierdzenie = (Button) findViewById(R.id.potwierdzenie);

        nazwa_leku.setText(getIntent().getExtras().getString("name"));
        posilek_leku.setText(getIntent().getExtras().getString("meal"));

        potwierdzenie.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(Notify.this, RingtonePlayingService.class);
                Notify.this.stopService(serviceIntent);
                finish();
            }


        });
    }
}
