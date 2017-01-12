package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowMedicine extends Activity {

    final Context context = this;
    MedsDatabase medi = new MedsDatabase(this);
    TextView tvMedName, tvMedHours, tvMedAddInfo;
    Button bEdit, bDelete, bCancel;
    String[] mealArray = new String[] {"przed posiłkiem", "w trakcie posiłku", "po posiłku"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_medicine);

        tvMedName = (TextView) findViewById(R.id.tvMedName);
        tvMedHours = (TextView) findViewById(R.id.tvMedHours);
        tvMedAddInfo = (TextView) findViewById(R.id.tvMedAddInfo);
        bEdit = (Button) findViewById(R.id.bEdit);
        bDelete = (Button) findViewById(R.id.bDelete);
        bCancel = (Button) findViewById(R.id.bCancel);

        final String MedId = getIntent().getExtras().getString("chosenId");
        tvMedName.setText(getIntent().getExtras().getString("chosenName"));
        tvMedHours.setText(getIntent().getExtras().getString("chosenHour"));
        tvMedAddInfo.setText(getIntent().getExtras().getString("chosenMeal"));


        bEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowMedicine.this, EditMedicine.class);
                intent.putExtra("chosenId", MedId);
                startActivity(intent);
            }
        });

        bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medi.deleteMed(getIntent().getExtras().getInt("chosenId"));
                Toast.makeText(getApplicationContext(),
                        "Usunięto", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ShowMedicine.this, MedicinesList.class));
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowMedicine.this, MedicinesList.class));
            }
        });

    }

}


