package com.example.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMedicine extends Activity {

    MedsDatabase medi = new MedsDatabase(this);
    Button send;
    EditText name;
    TimePicker time;
    Context context;
    String hour, minute, meal;
    ListView lv;
    String[] mealArray;

    // do obsługi powiadomień
    AlarmManager alarm;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_medicine);
        this.context = this;

        mealArray = new String[] {getString(R.string.przed),getString(R.string.wtrakcie), getString(R.string.po_posilku)};

        name = (EditText)findViewById(R.id.editText);
        time = (TimePicker)findViewById(R.id.timePicker);
        send=(Button)findViewById(R.id.Send);
        lv = (ListView)findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice, mealArray);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        time.setIs24HourView(true); // format 24h


        // do obsługi powiadomień
        final Calendar calendar = Calendar.getInstance();

        alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Intent intent = new Intent(this.context, Alarm.class);
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
                        R.string.zapisano, Toast.LENGTH_SHORT).show();

                // do obsługi powiadomień
                intent.putExtra("name",name.getText().toString() );
                intent.putExtra("meal", meal);
                pendingIntent = PendingIntent.getBroadcast(AddMedicine.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                finish();
            }
        });

    }
}
