package com.example.withawatbun.loginsharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_info);

        SharedPreferences sharedPreferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
        String display = sharedPreferences.getString("display","");

        TextView displayinfo = findViewById(R.id.txt_122);
        displayinfo.setText(display);
    }
}
