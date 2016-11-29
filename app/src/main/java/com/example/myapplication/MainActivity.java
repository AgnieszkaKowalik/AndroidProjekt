package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

    public Button today, user_guardian, calendar, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_main);
        init(); //wywołanie klasy init, która odpowiada za przejście do kolejnych aktywności po kliknięciu przycisku
    }

    public void init(){
        today = (Button)findViewById(R.id.today); //przycisk "Plan Dnia"
        today.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,DayPlans.class);
                startActivity(intent);
            }
        });
        calendar = (Button)findViewById(R.id.calendar); //przycisk "Kalendarz"
        calendar.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,CalendarView.class);
                startActivity(intent);
            }
        });
        settings = (Button)findViewById(R.id.settings); //przycisk "Ustawienia"
        settings.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });
        user_guardian = (Button)findViewById(R.id.user_guardian); //przycisk "Konto opiekuna"
        user_guardian.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,CaregiverAccount.class);
                startActivity(intent);
            }
        });
    }


    public void clickExit(View v) {
        finish(); //zamyka aplikację po kliknięciu na przycisk exit
    }

    @Override
    public void onBackPressed() {
        //po kliknięciu przyciusku "wstecz" wyświetla komunikat "Czy na pewno chcesz zamknąć aplikację?"
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Czy na pewno chcesz wyjść?");
        builder.setCancelable(true);
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



}
