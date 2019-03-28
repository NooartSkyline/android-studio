package com.example.multithread;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    public TextView tv_Thead_1,tv_Thead_2,tv_Thead_3;
    private int values;
    Thread t1,t2,t3;
    private String Thead_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity main1 = new MainActivity();
        t1 = new Thread(main1,"A");
        t2 = new Thread(main1,"B");
        t3 = new Thread(main1,"C");

        tv_Thead_1 = findViewById(R.id.tv_Thead_1);
        tv_Thead_2 = findViewById(R.id.tv_Thead_2);
        tv_Thead_3 = findViewById(R.id.tv_Thead_3);
        t1.start();
        t2.start();
        t3.start();

        tv_Thead_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("aaa","Skyline");
                startActivity(intent);
            }
        });

    }

    @Override
    public void run() {
        runThread1();

    }
//ถ้าไม่ใส่ synchronized มันจะมีโอกาสที่จะเข้าพร้อมกัน โดยที่ไม่สนใจว่า Thead ตัวไหนยังทำงานอยู่
    public synchronized void runThread1() {
        for (int i = 0; i <= 4; i++) {
            values++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i("thread", String.valueOf(values) + " " + Thread.currentThread().getName());
        }
    }


}
