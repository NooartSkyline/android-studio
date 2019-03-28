package com.example.withawatbun.sqlift;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import static android.provider.BaseColumns._ID;
import static com.example.withawatbun.sqlift.Constants.CONTENT;
import static com.example.withawatbun.sqlift.Constants.TABLE_NAME;
import static com.example.withawatbun.sqlift.Constants.TIME;

public class MainActivity extends AppCompatActivity {

    private Button add_butt ,btn_C;
    private EditText add_box;
    private TextView add_view;
    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        try {
            Cursor cursor = getAllNotes();
            showNotes(cursor);
        } finally {
            helper.close();
        }

        add_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    addNote(add_box.getText().toString());
                    Cursor cursor = getAllNotes();
                    showNotes(cursor);
                    add_box.setText(null);
                } finally {
                    helper.close();
                }
            }
        });
        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

                if (c.moveToFirst()) {
                    while ( !c.isAfterLast() ) {
                        Toast.makeText(MainActivity.this, "Table Name=> "+c.getString(0), Toast.LENGTH_SHORT).show();
                        c.moveToNext();
                    }
                }
            }
        });
    }

        private void addNote (String str){
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(TIME, System.currentTimeMillis());
            values.put(CONTENT, str);
            db.insertOrThrow(TABLE_NAME, null, values);
        }

        private static String[] COLUMNS = {_ID, TIME, CONTENT};
        private static String ORDER_BY = TIME + " DESC";

        private Cursor getAllNotes () {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, ORDER_BY);
            return cursor;
        }

        private void showNotes (Cursor cursor){
            StringBuilder builder = new StringBuilder("ข้อความที่บันทึกไว้:\n\n");

            while (cursor.moveToNext()) {
                long id = cursor.getLong(0);
                long time = cursor.getLong(1);
                String content = cursor.getString(2);

                builder.append("ลำดับ ").append(id).append(": ");

                String strDate = (String) DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date(time));
                builder.append(strDate).append("\n");
                builder.append("\t").append(content).append("\n\n");
            }

            add_view.setText(builder);
        }

        public void init () {
            add_butt = findViewById(R.id.add_butt);
            add_box = findViewById(R.id.add_box);
            add_view = findViewById(R.id.add_view);
            btn_C = findViewById(R.id.btn_click);
            helper = new DBHelper(this);
        }
    }

