package com.naijab.simpleactivityforresult;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_MAIN = 11;
    private static final String VALUE_KEY = "value";

    private Button btnGo;
    private TextView txtValue;
    private String valueFromSecondActivity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setUpView();
    }

    private void setUpView() {
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOpenSecondActivity();
            }
        });
    }

    private void bindView() {
        btnGo = (Button) findViewById(R.id.btn_to_second_activity);
        txtValue = (TextView) findViewById(R.id.tv_from_second_activity);
    }

    private void getOpenSecondActivity() {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivityForResult(intent, REQUEST_MAIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_MAIN) {
            if (resultCode == RESULT_OK){
                valueFromSecondActivity = data.getStringExtra(VALUE_KEY);
                txtValue.setText(valueFromSecondActivity);
            }else if(resultCode == RESULT_CANCELED){
                //TODO Handle Result Cancel
            }
        }

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.xxx);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(VALUE_KEY, valueFromSecondActivity);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        valueFromSecondActivity = savedInstanceState.getString(VALUE_KEY);
        txtValue.setText(valueFromSecondActivity);
    }
}
