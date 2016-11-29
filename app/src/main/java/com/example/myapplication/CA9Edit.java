package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CA9Edit extends AppCompatActivity {

    DBMeds medi = new DBMeds(this);
    Button send;
    EditText name;
    TimePicker time;
    Context context;
    AlarmManager am;
    String hour, minute, meal;
    ListView lv;
    String[] mealArray = new String[] {"przed posiłkiem", "w trakcie posiłku", "po posiłku"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca9_edit);

        this.context = this;

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        name = (EditText)findViewById(R.id.editText);
        time = (TimePicker)findViewById(R.id.timePicker);
        send=(Button)findViewById(R.id.Send);
        lv = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, mealArray);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        time.setIs24HourView(true); // format 24h

        final Calendar calendar = Calendar.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                // obsługa czasu
                calendar.set(Calendar.HOUR_OF_DAY, time.getCurrentHour());
                calendar.set(Calendar.MINUTE, time.getCurrentMinute());

                hour = time.getCurrentHour().toString();
                minute = time.getCurrentMinute().toString();

                if (time.getCurrentMinute()<10) {
                    minute = "0" + time.getCurrentMinute().toString();
                }

                SparseBooleanArray checked = lv.getCheckedItemPositions();
                for (int i = 0; i < lv.getAdapter().getCount(); i++) {
                    if (checked.get(i)) {
                        meal = String.valueOf(lv.getItemAtPosition(i));
                    }
                }

                //edycja leku
                Medicament medicament = new Medicament();
                medicament.setName(name.getText().toString());
                medicament.setHour(hour + ":" + minute);
                medicament.setMeal(meal);
                medi.updateMed(medicament);

                Toast.makeText(getApplicationContext(),
                        "Zaktualizowano", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(CA9Edit.this, CA4MedicinesList.class));
            }
        });

    }
}
