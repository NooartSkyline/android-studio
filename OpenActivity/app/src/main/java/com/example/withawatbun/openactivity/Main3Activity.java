package com.example.withawatbun.openactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView = findViewById(R.id.textView3);

        Bundle bundle = getIntent().getExtras();
        String Str = bundle.getString("btn3");

        textView.setText(Str);
        Toast.makeText(this, Str, Toast.LENGTH_SHORT).show();
    }
}
