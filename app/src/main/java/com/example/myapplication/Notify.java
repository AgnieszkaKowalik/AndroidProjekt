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
    TextView czy_wziety;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_notification);

        nazwa_leku = (TextView) findViewById(R.id.nazwa_leku);
        czy_wziety = (TextView) findViewById(R.id.czy_wziety);
        Button potwierdzenie = (Button) findViewById(R.id.potwierdzenie);


        potwierdzenie.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                // metoda która wypisuje tekst że lek został wzięty
                //set_text(getString(R.string.lek_wziety));
                Intent serviceIntent = new Intent(Notify.this, RingtonePlayingService.class);
                Notify.this.stopService(serviceIntent);
                finish();
            }


        });
    }

    private void set_text(String s) {
        czy_wziety.setText(s);
    }
}
