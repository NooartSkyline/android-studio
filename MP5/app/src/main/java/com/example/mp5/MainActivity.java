package com.example.mp5;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tv_OpenMusic, tv_howto;
    MediaPlayer player = null;
    private int count = 0;

    ArrayList<String> list;
    ArrayAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_OpenMusic = findViewById(R.id.tv_OpenMusic);
        tv_howto = findViewById(R.id.tv_howto);
        player = MediaPlayer.create(MainActivity.this, R.raw.kaonmasala);


        listView = findViewById(R.id.listView);
        list = new ArrayList<>();

        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            list.add(fields[i].getName());
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (player != null) {
                    player.release();
                }
                int resId = getResources().getIdentifier(list.get(position), "raw", getPackageName());
                player = MediaPlayer.create(MainActivity.this, resId);
                player.start();
                count = 1;
                tv_OpenMusic.setText("Pause");
            }
        });

        tv_howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, How_to.class);
                startActivity(intent);
            }
        });

        tv_OpenMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 0) {

                    if (player != null) {
                        player.start();
                        count = 1;
                        tv_OpenMusic.setText("Pause");
                    }

                } else {
                    if (player != null) {
                        player.pause();
                        count = 0;
                        tv_OpenMusic.setText("Play");
                    }
                }

            }
        });
        tv_OpenMusic.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (player != null) {
                    player.release();
//                    player.stop();
                    count = 0;
                    tv_OpenMusic.setText("Play");
                    player = MediaPlayer.create(MainActivity.this, R.raw.boypanom);
                    Toast.makeText(MainActivity.this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
}
