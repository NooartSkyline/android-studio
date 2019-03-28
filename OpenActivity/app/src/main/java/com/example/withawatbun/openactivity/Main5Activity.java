package com.example.withawatbun.openactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        textView = findViewById(R.id.textView5);

        Bundle bundle = getIntent().getExtras();
        String Str5 = bundle.getString("btn5");

        textView.setText(Str5);
        Toast.makeText(this, Str5, Toast.LENGTH_SHORT).show();
    }
}
