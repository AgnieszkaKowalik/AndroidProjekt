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

public class CA4MedicinesList extends Activity {

    DBMeds medi = new DBMeds(this);
    TextView mediList;
    ListView lvMeds; // do wyświetlania listy leków, na razie nie używane

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_ca4_medicines_list);

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
        //lvMeds.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lvMeds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String chosenMed = (String) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(),CA8ShowMedicine.class);
                String chosenMedName = medi.getMedName(i+1);
                String chosenMedHour = medi.getMedHour(i+1);
                String chosenMedMeal = medi.getMedMeal(i+1);
                intent.putExtra("chosenId", i+1);
                intent.putExtra("chosenName", chosenMedName);
                intent.putExtra("chosenHour", chosenMedHour);
                intent.putExtra("chosenMeal", chosenMedMeal);
                startActivity(intent);
                //startActivity(new Intent(view.getContext(), CA8ShowMedicine.class));
            }
        });
    }

    @Override
    public void onBackPressed(){ //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do CA3Hello
        Intent intent = new Intent(CA4MedicinesList.this, CA3Hello.class);
        startActivity(intent);
    }

}
