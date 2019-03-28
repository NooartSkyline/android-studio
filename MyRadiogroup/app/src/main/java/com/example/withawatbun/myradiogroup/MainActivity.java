package com.example.withawatbun.myradiogroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_a);

        radioGroup = findViewById(R.id.radioGroup);
      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Toast.makeText(MainActivity.this, "Aty", Toast.LENGTH_SHORT).show();
          }
      });
    }

    public void checket(View view) {
        int rb = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(rb);
        Toast.makeText(this, String.valueOf(rb) + " " +radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}
