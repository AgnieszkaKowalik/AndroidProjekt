package com.example.myapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity {

    public Button today, user_guardian, list, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init(); //wywołanie klasy init, która odpowiada za przejście do kolejnych aktywności po kliknięciu przycisku
    }

    public void init(){
        today = (Button)findViewById(R.id.today); //przycisk "Plan Dnia"
        today.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, DayPlans.class);
                startActivity(intent);
            }
        });
        list = (Button)findViewById(R.id.lista); //przycisk "Lista Leków"
        list.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, PatientMedicineList.class);
                startActivity(intent);
            }
        });

        user_guardian = (Button)findViewById(R.id.user_guardian); //przycisk "Konto opiekuna"
        user_guardian.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CaregiverAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //po kliknięciu przyciusku "wstecz" wyświetla komunikat "Czy na pewno chcesz zamknąć aplikację?"
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(getString(R.string.czy_chcesz_wyjsc));
        builder.setCancelable(true);
        builder.setPositiveButton(getString(R.string.tak), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                MainActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton(getString(R.string.nie), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
