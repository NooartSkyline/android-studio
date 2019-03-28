package com.example.withawatbun.count;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private Button btn_Click;
    private EditText et_year,et_months,et_day;
    private TextView txt_Countdate;
    private int D,M,Y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Click = findViewById(R.id.btn_Click);
        et_year = findViewById(R.id.et_year);
        et_months = findViewById(R.id.et_months);
        et_day = findViewById(R.id.et_day);
        txt_Countdate = findViewById(R.id.txt_Countdate);

        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_day != null && !et_day.equals("") && et_months != null && !et_months.equals("")&& et_year != null && !et_year.equals("") ) {
                    D = Integer.parseInt(et_day.getText().toString());
                    M = Integer.parseInt(et_months.getText().toString());
                    Y = Integer.parseInt(et_year.getText().toString());

                    txt_Countdate.setText(BD(Y, M, D));
                }
            }
        });
    }

    public String BD (int Y, int M, int D){

        LocalDate datenow = LocalDate.now();
        LocalDate BD = LocalDate.of(Y,M,D);
        System.out.println(BD.until(datenow, ChronoUnit.DAYS));


               Y = BD.until(datenow).getYears();
               M = BD.until(datenow).getMonths();
               D = BD.until(datenow).getDays();
               String Date = "อายุ: " + Y + " ปี" + M + " เดือน"+ D + " วัน";
               return Date;
    }
}
