package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Agnieszka on 12.01.2017.
 */
public class PatientMedicineList extends Activity {

    MedsDatabase medi = new MedsDatabase(this);
    TextView mediList;
    ListView lvMeds; // do wyświetlania listy leków

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medicines_list);

        mediList = (TextView) findViewById(R.id.textView);
        lvMeds = (ListView) findViewById(R.id.lvMeds);

        List<Medicament> meds = medi.getAllMeds();
        final String[] arrMeds = new String[meds.size()];

        int i = 0;
        for (Medicament m : meds) {
            arrMeds[i] = m.getId() + " " + m.getName() + " " + m.getHour() + " " + m.getMeal();
            i++;
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrMeds);
        lvMeds.setAdapter(adapter);
    };

}
