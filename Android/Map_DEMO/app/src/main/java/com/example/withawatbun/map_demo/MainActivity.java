package com.example.withawatbun.map_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public DatabaseReference myRef;
    private TextView txt_Firebase;
    private EditText editText1,editText2;
    private String edit1,edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_Firebase = (TextView)findViewById(R.id.txt_Firebase);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);

        FirebaseDatabase databse = FirebaseDatabase.getInstance();
        myRef = databse.getReference();
        ReadDataFirebase();
    }

    public void ReadDataFirebase(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                Map map = (Map)dataSnapshot.getValue();
                String value = String.valueOf(map.get("Name"));
                String value2 = String.valueOf(map.get("lastname"));

                txt_Firebase.setText(value + " " + value2);
                Toast.makeText(MainActivity.this, "อัพเดทข้อมูลแล้ว", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void UpdateDataFirebase(View view) {

            edit1 = editText1.getText().toString();
            edit2 = editText2.getText().toString();

        if (edit1 != "") {

            Map<String, Object> Value = new HashMap<>();
            Value.put("Name", edit1);
            Value.put("lastname", edit2);
            myRef.updateChildren(Value);
//        Toast.makeText(this, "Success.", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, edit1 + edit2, Toast.LENGTH_SHORT).show();
            Clear();
        }else {
            Toast.makeText(this, "ใส่ข้อมูลก่อนนะ", Toast.LENGTH_SHORT).show();
            editText1.findFocus();
        }
    }
    public void Clear(){
        editText1.setText("");
        editText2.setText("");

    }
}
