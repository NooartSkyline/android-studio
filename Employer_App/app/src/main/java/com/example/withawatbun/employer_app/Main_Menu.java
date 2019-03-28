package com.example.withawatbun.employer_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main_Menu extends AppCompatActivity {
    private Button btn_click1,btn_click2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__menu);

        btn_click1 = findViewById(R.id.btn_a1);
        btn_click2 = findViewById(R.id.btn_a2);

        btn_click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Menu.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btn_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main_Menu.this,MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
