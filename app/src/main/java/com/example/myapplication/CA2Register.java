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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CA2Register extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    Button BReg2_Back, BReg1_Reg;

    public void init() {
        BReg2_Back = (Button)findViewById(R.id.BReg2_Back);
        BReg2_Back.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CA2Register.this,CaregiverAccount.class);
                startActivity(intent);
            }
        });
        BReg1_Reg = (Button)findViewById(R.id.BReg1_Reg);
        BReg1_Reg.setOnClickListener(new View.OnClickListener(){ //przejście do nowej activity po kliknięciu
            @Override
            public void onClick(View v){
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
                if(namestr.matches("")||phonestr.matches("")||unamestr.matches("")||pass1str.matches("")){
                    Toast empty = Toast.makeText(CA2Register.this, "Wszystkie pola muszą być wypełnione", Toast.LENGTH_LONG);
                    empty.show();
                }else{
                    if(!pass1str.equals(pass2str)){ //porównanie czy wpisane hasła są takie same - wyskakuje powiadomienie
                        Toast pass = Toast.makeText(CA2Register.this, "Wpisane hasła nie są takie same", Toast.LENGTH_SHORT );
                        pass.show();
                    } else { //wrzucenie danych do bazy
                        Contact c = new Contact();
                        c.setName(namestr);
                        c.setPhone(phonestr);
                        c.setUname(unamestr);
                        c.setPassword(pass1str);

                        helper.insertContact(c);

                        Toast toast = Toast.makeText(CA2Register.this, "Rejestracja przebiegła pomyślnie, zaloguj się", Toast.LENGTH_LONG);
                        toast.show();

                        Intent intent = new Intent(CA2Register.this,CaregiverAccount.class);
                        startActivity(intent);
                    }
                }



            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //żeby nie było paska w nagłówku - do tego na początku jest extends Activity a nie AppCompatActivity
        setContentView(R.layout.activity_ca2_register);
        init();
    }

    @Override
    public void onBackPressed(){  //żeby po kliknięciu "wstecz" nie zapętlało, tylko wróciło do CaregiverAccount
        Intent intent = new Intent(CA2Register.this, CaregiverAccount.class);
        startActivity(intent);
    }

}
