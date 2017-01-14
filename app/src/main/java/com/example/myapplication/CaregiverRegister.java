//trzeba dodać kilka ograniczeń na to co jest wpisywane do formularza - zeby np. telefon nie mógł się składać z 15 cyfr itp
//zeby hasło musiało zawierać co najmniej ?? znaków itp.
//dodatkowo dodać email
//zabezpieczyć baze przed SQL injection - zastrzeżenie, że w formularzu nie mogą sie znajdować jakieś znaki, np. średnik

package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class CaregiverRegister extends Activity {

    ContactsDatabase helper = new ContactsDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_caregiver_register);
    }

    public void backToLoginOnClick(View view) {
        Intent intent = new Intent(this, CaregiverAccount.class);
        startActivity(intent);
    }

    public void registerOnClick(View view) {
        //pobranie wartości z wszystkich pól EditText, żeby móc je porównać (hasła) i wrzucić do bazy
        EditText name = (EditText)findViewById(R.id.et1_name);
        EditText phone = (EditText)findViewById(R.id.et2_tel);
        EditText uname = (EditText)findViewById(R.id.et3_username);
        EditText pass1 = (EditText)findViewById(R.id.et4_pass);
        EditText pass2 = (EditText)findViewById(R.id.et5_pass2);

        String namestr = name.getText().toString();
        String phonestr = phone.getText().toString();
        String unamestr = uname.getText().toString();
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        if (namestr.isEmpty() || phonestr.isEmpty() || unamestr.isEmpty() || pass1str.isEmpty()) {
            Toast empty = Toast.makeText(this, R.string.wypelnione_pola, Toast.LENGTH_LONG);
            empty.show();
        } else if (!pass1str.equals(pass2str)){ //porównanie czy wpisane hasła są takie same - wyskakuje powiadomienie
            Toast pass = Toast.makeText(this, R.string.inne_hasla, Toast.LENGTH_SHORT );
            pass.show();
        } else { //wrzucenie danych do bazy
            Contact c = new Contact();
            c.setName(namestr);
            c.setPhone(phonestr);
            c.setUname(unamestr);
            c.setPassword(pass1str);

            helper.insertContact(c);

            Toast toast = Toast.makeText(this, R.string.super_resjestracja, Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(this, CaregiverAccount.class);
            startActivity(intent);
            finish();
        }
    }
}
