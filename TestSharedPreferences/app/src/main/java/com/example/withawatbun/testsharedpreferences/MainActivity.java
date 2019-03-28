package com.example.withawatbun.testsharedpreferences;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String P_NAME = "App_Config";
    EditText edtUserName;
    CheckBox cbRemember;
    Button btn_onClick;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Notification.Builder notification;
    final int id = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUserName = findViewById(R.id.edtUserName);
        cbRemember = findViewById(R.id.cbRemember);
        btn_onClick = findViewById(R.id.btn_Click);

        sp = getSharedPreferences(P_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();

//        editor.putBoolean("FirstRun", true);
//        editor.putString("name1", "Vittawat1");
//        editor.putString("name2", "Vittawat2");
//        editor.putString("name3", "Vittawat3");v
//        editor.putString("name4", "Vittawat4");
//        editor.putString("name5", "Vittawat5");


//        editor.remove("name");
//        editor.remove("FirstRun");
//        editor.apply();
        editor.commit();
//        Log.e("art",sp.getBoolean("FirstRun",false)+ "");

        Boolean Vcb = sp.getBoolean("FirstRun", false);
        String Ccb = String.valueOf(Vcb);
        String namey = sp.getString("name1", "Noname");
        String namey1 = sp.getString("name2", "Noname");
        String namey2 = sp.getString("name3", "Noname");
        String namey3 = sp.getString("name4", "Noname");
        String namey4 = sp.getString("name5", "Noname");
        cbRemember.setChecked(Vcb);
        edtUserName.setText(Ccb + " " + namey + namey1 + " " + namey2 + " " + namey3 + " " + namey4);



    }
    public void showNotification(View view) {
        Toast.makeText(this, "ตวายย", Toast.LENGTH_SHORT).show();
        notification = new Notification.Builder(MainActivity.this);
        notification.setSmallIcon(R.drawable.ic_launcher_foreground);
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("rtrtt");
        notification.setTicker("ข้อความใหม่");
        notification.setContentText("Holoool");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE
        );
        nm.notify(id,notification.build());
    }
}
