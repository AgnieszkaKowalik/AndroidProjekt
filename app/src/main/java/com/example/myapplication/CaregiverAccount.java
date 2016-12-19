package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CaregiverAccount extends Activity {

    Button ButSetAccount,ButLogIn;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_caregiver_account);
        init();
    }

    private void init() {
        ButSetAccount = (Button)findViewById(R.id.ButSetAccount);
        ButSetAccount.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CaregiverAccount.this,CA2Register.class);
                startActivity(intent);
            }
        });
        ButLogIn = (Button)findViewById(R.id.ButLogIn);
        ButLogIn.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                EditText a = (EditText)findViewById(R.id.ETusername);
                String str = a.getText().toString();
                EditText b = (EditText)findViewById(R.id.ETpassword);
                String pass = b.getText().toString();

                String password = helper.searchPass(str);
                if (pass.equals(password)) {
                    Intent intent = new Intent(CaregiverAccount.this, CA3Hello.class);
                    intent.putExtra("Username",str);  //wysyła nazwę uzytkownika do CA3Hello, gdzie jest wywołane "Username" przez putExtra
                    startActivity(intent);
                    finish();
                } else {
                    Toast temp = Toast.makeText(CaregiverAccount.this, "Błędne hasło", Toast.LENGTH_SHORT);
                    temp.show();
                }
            }
        });
    }
}
