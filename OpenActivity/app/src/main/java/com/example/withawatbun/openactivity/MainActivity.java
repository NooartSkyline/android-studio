package com.example.withawatbun.openactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button,button2,button3,button4,button5;
    String status = "1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button  = findViewById(R.id.button);
        button2  = findViewById(R.id.button2);
        button3  = findViewById(R.id.button3);
        button4  = findViewById(R.id.button4);
        button5  = findViewById(R.id.button5);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status.equals("1")) {
                    button2.setVisibility(View.GONE);
                    button3.setVisibility(View.GONE);
                    button4.setVisibility(View.GONE);
                    button5.setVisibility(View.GONE);
                    status = "0";
                }else {
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    button5.setVisibility(View.VISIBLE);
                    status = "1";

                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open2();

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open3();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open4();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open5();
            }
        });
    }
    public void open(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("btn" , "btn");
        startActivity(intent);

    }public void open2(){
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra("btn2" , "btn2");
        startActivity(intent);

    }public void open3(){
        Intent intent = new Intent(this,Main3Activity.class);
        intent.putExtra("btn3" , "btn3");
        startActivity(intent);

    }public void open4(){
        Intent intent = new Intent(this,Main4Activity.class);
        intent.putExtra("btn4" , "btn4");
        startActivity(intent);

    }public void open5(){
        Intent intent = new Intent(this,Main5Activity.class);
        intent.putExtra("btn5" , "btn5");
        startActivity(intent);
    }

}
