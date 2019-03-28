package com.naijab.simpleactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.app.Activity.RESULT_OK;

public class SecondActivity extends AppCompatActivity {

    private static final int REQUEST_MAIN = 11;
    private static final String VALUE_KEY = "value";

    private Button btnSubmit;
    private EditText edtValue;
    private String valueEditText = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bindView();
        setUpView();
    }

    private void setUpView() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueEditText  = edtValue.getText().toString();
                backToMainActivity();
            }
        });
    }

    private void bindView() {
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        edtValue = (EditText) findViewById(R.id.edt_text);
    }

    private void backToMainActivity() {
        Intent intent = new Intent();
        intent.putExtra(VALUE_KEY, valueEditText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VALUE_KEY, valueEditText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        valueEditText = savedInstanceState.getString(VALUE_KEY);
        edtValue.setText(valueEditText);
    }
}
