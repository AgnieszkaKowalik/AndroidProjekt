package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class CA5AddMedicine extends Activity {

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
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_ca5_add_medicine);
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

                // dodawanie nowego leku do bazy
                Medicament newMed = new Medicament();
                newMed.setName(name.getText().toString());
                newMed.setHour(hour + ":" + minute);
                newMed.setMeal(meal);
                medi.insertMed(newMed);

                Toast.makeText(getApplicationContext(),
                        "Zapisano!", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(CA5AddMedicine.this, CA3Hello.class));
            }
        });

    }

    @Override
    public void onBackPressed(){  //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do CA3Hello
        Intent intent = new Intent(CA5AddMedicine.this, CA3Hello.class);
        startActivity(intent);
    }


}
