package com.example.withawatbun.openactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

        TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView2);

        Bundle bundle = getIntent().getExtras();
        String sas = bundle.getString("btn2");

        textView.setText(sas);
        Toast.makeText(this, sas, Toast.LENGTH_SHORT).show();

    }
}
