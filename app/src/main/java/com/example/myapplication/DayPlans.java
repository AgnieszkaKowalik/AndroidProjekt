package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class DayPlans extends Activity {

 //   DBMeds medi = new DBMeds(this);   //nie działa
 //   TextView mediList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_day_plans);

 //       mediList=(TextView)findViewById(R.id.textView2);
  //      String medicaments = medi.searchMedi();
 //       mediList.setText(medicaments);
    }

    @Override
    public void onBackPressed(){  //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do MainActivity
        Intent intent = new Intent(DayPlans.this, MainActivity.class);
        startActivity(intent);
    }
}
