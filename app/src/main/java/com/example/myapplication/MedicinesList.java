package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MedicinesList extends Activity {

    MedsDatabase medi = new MedsDatabase(this);
    TextView mediList;
    ListView lvMeds; // do wyświetlania listy leków

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_medicines_list);

        mediList=(TextView)findViewById(R.id.textView);
        lvMeds = (ListView)findViewById(R.id.lvMeds);

        List<Medicament> meds= medi.getAllMeds();
        String [] arrMeds = new String[meds.size()];

            int i=0;
            for(Medicament m:meds) {
                arrMeds[i] = m.getId() + " " + m.getName() + " " + m.getHour() + " " + m.getMeal();
                i++;
            }

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrMeds);
        lvMeds.setAdapter(adapter);

        lvMeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(),ShowMedicine.class);
                String chosenMedName = medi.getMedName(i+1);
                String chosenMedHour = medi.getMedHour(i+1);
                String chosenMedMeal = medi.getMedMeal(i+1);
                intent.putExtra("chosenId", i+1);
                intent.putExtra("chosenName", chosenMedName);
                intent.putExtra("chosenHour", chosenMedHour);
                intent.putExtra("chosenMeal", chosenMedMeal);
                startActivity(intent);
            }
        });
    }
}
