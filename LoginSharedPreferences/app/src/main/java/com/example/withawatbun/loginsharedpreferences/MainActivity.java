package com.example.withawatbun.loginsharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    TextView txt_register;
    Button btn_Logon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        txt_register = findViewById(R.id.txt_register);

        btn_Logon = findViewById(R.id.btn_login);

        btn_Logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                SharedPreferences sharedPreferences = getSharedPreferences("MYPEFS",MODE_PRIVATE);

                String userDetail = sharedPreferences.getString(user + pass + "data" ,"Usernaem or Password is Incorrect.");
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("display",userDetail);
                editor.commit();

                Intent displayScreen = new Intent(MainActivity.this,DisplayScreen.class);
                startActivity(displayScreen);
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);

            }
        });

    }
}
