package com.example.myapplication;

//nie do końca działa - wyświetlanie username

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CA3Hello extends Activity {

    Button medList, addNew, noti, change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_ca3__hello);
        String username = getIntent().getStringExtra("Username");   //nie działa - na początku wyświetla nazwę, ale gdy drugi raz okienko zostanie wyświetlone (np
                                                                    //po dodaniu leku - wtedy pokazuje username jako "null". Może lepiej jakoś z bazy pobrać.
        TextView textView = (TextView)findViewById(R.id.TVusername);
        textView.setText(username+",");
        init();
    }

    public void init(){
        medList = (Button)findViewById(R.id.Check);
        addNew = (Button)findViewById(R.id.Add);
        noti = (Button)findViewById(R.id.Alarms);
        change = (Button)findViewById(R.id.Data);

        medList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CA3Hello.this, CA4MedicinesList.class);
                startActivity(intent);
            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CA3Hello.this, CA5AddMedicine.class);
                startActivity(intent);
            }
        });
        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CA3Hello.this,CA6NotificationSettings.class);
                startActivity(intent);
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CA3Hello.this, CA7ChangeAccountInformations.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed(){ //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do CaregiverAccount
        Intent intent = new Intent(CA3Hello.this, CaregiverAccount.class);
        startActivity(intent);
    }

}
