package com.example.withawatbun.my_vibrator;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_1, btn_2, btn_3;
    private Switch sw_3;
    private Vibrator vibrator;
    private boolean string_sw;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        sw_3 = findViewById(R.id.sw_3);


        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string_sw =  sw_3.isChecked();
                if(string_sw == true) {
                    vibrator.vibrate(50);
                }else {
                    vibrator.vibrate(200);
                }
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(string_sw == true) {
                    vibrator.vibrate(500);
                }else {
                    vibrator.vibrate(1000);
                }
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(string_sw == true) {
                    vibrator.vibrate(1000);
                }else {
                    vibrator.vibrate(2000);
                }
            }
        });

        sw_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    string_sw = isChecked;
                    Toast.makeText(MainActivity.this, String.valueOf(string_sw), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, String.valueOf(isChecked), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
