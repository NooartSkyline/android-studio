package com.example.withawatbun.loginsharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity{

    EditText edit_name,edit_password,edit_email;
    Button btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        edit_name = findViewById(R.id.edt_name);
        edit_password = findViewById(R.id.edt_password);
        edit_email = findViewById(R.id.edt_Email);
        btn_Register = findViewById(R.id.btn_Register);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MYPEFS", MODE_PRIVATE);
                String newUser = edit_name.getText().toString();
                String newpass = edit_password.getText().toString();
                String newemail = edit_email.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(newUser + newpass + "data" , newUser + "/n" + newemail);
                editor.commit();

                Intent intentLogin = new Intent(Register.this,MainActivity.class);
                startActivity(intentLogin);
            }
        });
    }
}
