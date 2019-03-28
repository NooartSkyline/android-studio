package com.example.multithread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements Runnable {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = findViewById(R.id.button);

        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("aaa");

        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

        Thread t1 = new Thread(new Main2Activity(), "t1");
        Thread t2 = new Thread(new Main2Activity(), "t2");
        Thread t3 = new Thread(new Main2Activity(), "t3");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        t1.start();
        try {
            t1.join(3000);//ผ่านไป 3 วิ t2 จะ Start
        } catch (Exception e) {
            e.printStackTrace();
        }
        t2.start();
        try{
            t2.join(3000);
        }catch (Exception e) {
            e.printStackTrace();
        }
        t3.start();
        try{
           t3.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.i("thead", "จบการทำงาน");
    }

    @Override
    public void run() {
        Log.i("thead", "Thead_start : " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("thead", "Thead_end : " + Thread.currentThread().getName());
    }
}
