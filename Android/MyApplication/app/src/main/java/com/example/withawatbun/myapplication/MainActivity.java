package com.example.withawatbun.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    int secondss;
    String art = "ไอ้ควาย";
    String Status1 = "1" , Status2;
    private TextView TextView1;
    private Button btn_Run;
    int Random_num1 ;
    int TextSize = 35;
    String TextColor = "#D02090";
    private boolean mTimerRunning;
    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 1000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        TextView1 = (TextView) findViewById(R.id.txtView1);
        btn_Run = (Button) findViewById(R.id.btn_Run);

        TextView1.setEnabled(false);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rdb_type1:
                if (checked)
                    // Pirates are the best
                    Status1 = "1";
                    Status2 = "0";
//                    Toast.makeText(this, String.valueOf(checked)+ Status1 + Status2, Toast.LENGTH_SHORT).show();

                    break;
            case R.id.rdb_type2:
                if (checked)
                    // Ninjas rule
                    Status1 = "0";
                    Status2 = "1";
//                    Toast.makeText(this, String.valueOf(checked)+ Status1 + Status2 , Toast.LENGTH_SHORT).show();

                break;
        }
    }
    public void Clear(View view) {
//        Toast.makeText(this, art, Toast.LENGTH_SHORT).show();
        TextView1.setText("");
    }
    public void Exit_App(View view) {
        onBackPressed();
//        resetTimer();
    }
    public void Randomm (View view ) {
        TextView1.setTextSize(TextSize);
        TextView1.setTextColor(Color.parseColor(TextColor));
        Random rand = new Random();
        Random_num1 = rand.nextInt(10);
//        Toast.makeText(this, String.valueOf(Random_num1), Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, Random_num1, Toast.LENGTH_SHORT).show();
        if (Status1 == "1") {
            if (Random_num1 == 0) {
                TextView1.setText("กระเพราหมูสับไข่ดาว");
            } else if (Random_num1 == 1) {
                TextView1.setText("กระเพราเนื้อเปื่อย");
            } else if (Random_num1 == 2) {
                TextView1.setText("ข้าวผัดต้มยำ");
            } else if (Random_num1 == 3) {
                TextView1.setText("ข้าวคลุกกระปิ");
            } else if (Random_num1 == 4) {
                TextView1.setText("ข้าวไข่เจียว");
            } else if (Random_num1 == 5) {
                TextView1.setText("ข้าวผัดหมู,ไก่");
            } else if (Random_num1 == 6) {
                TextView1.setText("หมูทอดกระเทียม");
            } else if (Random_num1 == 7) {
                TextView1.setText("ก๋วยเตี๋ยวเล็กน้ำตกเนื้อเปื่อย");
            } else if (Random_num1 == 8) {
                TextView1.setText("ลาบขม+ต้มแซ่บ");
            } else if (Random_num1 == 9) {
                TextView1.setText("ก๋วยจั๊บรวม+ไข่");
            } else if (Random_num1 == 10) {
                TextView1.setText("ต้มยำทะเล");
            }
        }

            if (Status2 == "1") {
                if (Random_num1 == 0) {
                    TextView1.setText("Tiger Beer");
                } else if (Random_num1 == 1) {
                    TextView1.setText("Federbrau");
                } else if (Random_num1 == 2) {
                    TextView1.setText("San Miguel / Sapporo");
                } else if (Random_num1 == 3) {
                    TextView1.setText("Kirin Ichiban");
                } else if (Random_num1 == 4) {
                    TextView1.setText("Heineken");
                } else if (Random_num1 == 5) {
                    TextView1.setText("Hoegaarden");
                } else if (Random_num1 == 6) {
                    TextView1.setText("Chang");
                } else if (Random_num1 == 7) {
                    TextView1.setText("Singha");
                } else if (Random_num1 == 8) {
                    TextView1.setText("Leo");
                } else if (Random_num1 == 9) {
                    TextView1.setText("Corona");
                } else if (Random_num1 == 10) {
                    TextView1.setText("HOEGAARDEN");
                }
            }

//        TextView1.setText(String.valueOf(Random_num1));
//        Toast.makeText(this, art, Toast.LENGTH_SHORT).show();

    }
    public void Count (View view) {

        if (mTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
            }

            @Override
            public void onFinish() {
//                mTimerRunning = false;
//                btn_Run.setText("RUN");
//                btn_Run.setTextSize(45);
                startTimer();
//                mTimerRunning = true;

//                mButtonStartPause.setVisibility(View.INVISIBLE);
//                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        btn_Run.setText("pause");
        btn_Run.setTextSize(45);
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        btn_Run.setText("Start");
        btn_Run.setTextSize(45);
    }
    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
//        mButtonReset.setVisibility(View.INVISIBLE);
//        mButtonStartPause.setVisibility(View.VISIBLE);
    }
    private void updateCountDownText() {
        TextView1.setTextSize(TextSize);
        TextView1.setTextColor(Color.parseColor(TextColor));
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
         secondss = (int) (mTimeLeftInMillis / 100);

        TextView1.setText(String.valueOf(secondss));

        if (Status1 == "1") {
            if (secondss == 0) {
                TextView1.setText("กระเพราหมูสับไข่ดาว");
            } else if (secondss == 1) {
                TextView1.setText("กระเพราเนื้อเปื่อย");
            } else if (secondss == 2) {
                TextView1.setText("ข้าวผัดต้มยำ");
            } else if (secondss == 3) {
                TextView1.setText("ข้าวคลุกกระปิ");
            } else if (secondss == 4) {
                TextView1.setText("ข้าวไข่เจียว");
            } else if (secondss == 5) {
                TextView1.setText("ข้าวผัดหมู,ไก่");
            } else if (secondss == 6) {
                TextView1.setText("หมูทอดกระเทียม");
            } else if (secondss == 7) {
                TextView1.setText("ก๋วยเตี๋ยวเล็กน้ำตกเนื้อเปื่อย");
            } else if (secondss == 8) {
                TextView1.setText("ลาบขม+ต้มแซ่บ");
            } else if (secondss == 9) {
                TextView1.setText("ก๋วยจั๊บรวม+ไข่");
            } else if (secondss == 10) {
                TextView1.setText("ต้มยำทะเล");
            }
        }

        if (Status2 == "1") {
            if (secondss == 0) {
                TextView1.setText("Tiger Beer");
            } else if (secondss == 1) {
                TextView1.setText("Federbrau");
            } else if (secondss == 2) {
                TextView1.setText("San Miguel / Sapporo");
            } else if (secondss == 3) {
                TextView1.setText("Kirin Ichiban");
            } else if (secondss == 4) {
                TextView1.setText("Heineken");
            } else if (secondss == 5) {
                TextView1.setText("Hoegaarden");
            } else if (secondss == 6) {
                TextView1.setText("Chang");
            } else if (secondss == 7) {
                TextView1.setText("Singha");
            } else if (secondss == 8) {
                TextView1.setText("Leo");
            } else if (secondss == 9) {
                TextView1.setText("Corona");
            } else if (secondss == 10) {
                TextView1.setText("HOEGAARDEN");
            }
        }

//        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
//
//        TextView1.setText(timeLeftFormatted);
    }

}

