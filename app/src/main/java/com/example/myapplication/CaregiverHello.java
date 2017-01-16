package com.example.myapplication;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CaregiverHello extends Activity {

    Button medList, addNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_caregiver_hello);
        String username = getIntent().getStringExtra("Username");
        TextView textView = (TextView)findViewById(R.id.TVusername);
        textView.setText(" " + username + ",");
        init();
    }

    public void init(){
        medList = (Button)findViewById(R.id.Check);
        addNew = (Button)findViewById(R.id.Add);

        medList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverHello.this, MedicinesList.class);
                startActivity(intent);
            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaregiverHello.this, AddMedicineActivity.class);
                startActivity(intent);
            }
        });
    }
}
