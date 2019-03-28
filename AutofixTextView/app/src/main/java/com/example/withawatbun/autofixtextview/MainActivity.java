package com.example.withawatbun.autofixtextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.grantland.widget.AutofitTextView;

public class MainActivity extends AppCompatActivity {

    AutofitTextView mTextView;
    TextView mTextView1;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView =  findViewById(R.id.text_view);
        mEditText =  findViewById(R.id.edit_text);
        mTextView1 = findViewById(R.id.textView);


        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextView.setText(s);
                mTextView1.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
