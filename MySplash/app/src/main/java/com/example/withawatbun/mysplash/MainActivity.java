package com.example.withawatbun.mysplash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView25);
        textView2 = (TextView)findViewById(R.id.textView26);


            Bundle bundle = getIntent().getExtras();
             Model model = (Model) bundle.get("model");
             ArrayList<Model> list = (ArrayList<Model>) bundle.get("List");
             String aa =(String) bundle.get("aa");


//            textView.setText(model.name);
            textView.setText(aa);
            textView2.setText(list.get(0).name);



    }
}
