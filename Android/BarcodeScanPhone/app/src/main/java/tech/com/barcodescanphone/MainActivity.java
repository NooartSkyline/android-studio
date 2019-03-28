package tech.com.barcodescanphone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import tech.com.barcodescanphone.Scanner.ScannerActivity;

public class MainActivity extends AppCompatActivity {

    public final static String CONTENT = "content";
    private final static int REQUEST_SCANNER = 1;
    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 16000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean mTimerRunning;
    TextView result_scan;
    ImageView imv_but;
    Switch sw;
    int seconds ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        result_scan = findViewById(R.id.result_scan);
        imv_but = findViewById(R.id.imvbut);
        sw = findViewById(R.id.sw_Random);
        imv_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ScannerActivity.class);
                startActivityForResult(intent, REQUEST_SCANNER);
            }
        });
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if(isCheck){
                   Count();

                }else {
                    pauseTimer();
                    Toast.makeText(MainActivity.this, "OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Count () {

        if (mTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
            }

            @Override
            public void onFinish() {
                startTimer();
            }
        }.start();
        mTimerRunning = true;
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                result_scan.setText(data.getStringExtra(CONTENT));

            }
        }
    private void updateCountDownText() {
        seconds = (int) (mTimeLeftInMillis / 1000);
//        result_scan.setText(String.valueOf(seconds));

        if (seconds == 0) {
            imv_but.setImageResource(R.drawable.b8);
        } else if (seconds == 1) {
            imv_but.setImageResource(R.drawable.b3);
        } else if (seconds == 2) {
            imv_but.setImageResource(R.drawable.b6);
        } else if (seconds == 3) {
            imv_but.setImageResource(R.drawable.b5);
        } else if (seconds == 4) {
            imv_but.setImageResource(R.drawable.b4);
        } else if (seconds == 5) {
            imv_but.setImageResource(R.drawable.b7);
        } else if (seconds == 6) {
            imv_but.setImageResource(R.drawable.b2);
        } else if (seconds == 7) {
            imv_but.setImageResource(R.drawable.b9);
        } else if (seconds == 8) {
            imv_but.setImageResource(R.drawable.b10);
        } else if (seconds == 9) {
            imv_but.setImageResource(R.drawable.b11);
        } else if (seconds == 10) {
            imv_but.setImageResource(R.drawable.b12);
        } else if (seconds == 11) {
            imv_but.setImageResource(R.drawable.b17);
        } else if (seconds == 12) {
            imv_but.setImageResource(R.drawable.b13);
        } else if (seconds == 13) {
            imv_but.setImageResource(R.drawable.b14);
        } else if (seconds == 14) {
            imv_but.setImageResource(R.drawable.b15);
        } else if (seconds == 15) {
            imv_but.setImageResource(R.drawable.b16);
        } else if (seconds == 16) {
            imv_but.setImageResource(R.drawable.b1);
        }
    }
}

