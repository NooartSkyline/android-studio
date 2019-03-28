package com.example.withawatbun.openactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        textView = findViewById(R.id.textView4);

        Bundle bundle = getIntent().getExtras();
        String Str4 = bundle.getString("btn4");

        textView.setText(Str4);
        Toast.makeText(this, Str4, Toast.LENGTH_SHORT).show();
    }
}
