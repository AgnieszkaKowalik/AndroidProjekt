package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class CA7ChangeAccountInformations extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_ca7_change_account_informations);
    }
}
