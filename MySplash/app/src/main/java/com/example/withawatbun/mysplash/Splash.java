package com.example.withawatbun.mysplash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {

                    sleep(1000);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    ArrayList<Model> list = new ArrayList<>();
                    Model item2 = new Model();

                    item2.name = "art";
                    item2.lastname = "skyline";
                    item2.user = "skyline";
                    item2.pass = "abc";
                    item2.email = "nooartskyline@gmail.com";
                    item2.tel = "0221455555";
                    item2.addrass = "ubonratchatani";
                    list.add(item2);
//
                    item2 = new Model();
                    item2.name = "arty";
                    item2.lastname = "skyline";
                    item2.user = "skyline";
                    item2.pass = "abc";
                    item2.email = "nooartskyline@gmail00000.com";
                    item2.tel = "0221455555";
                    item2.addrass = "ubonratchatani";

                    list.add(item2);

                    String art = new Gson().toJson(list);//แปลง กลับไปเป็น Json
//                    Toast.makeText(Splash.this, art, Toast.LENGTH_SHORT).show();

                    intent.putExtra("model", list.get(1));
                    intent.putExtra("List", list);
                    intent.putExtra("aa", art);

                    startActivity(intent);
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }
}
