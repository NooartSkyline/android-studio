package apps_dohomeapp.dohome.co.th.counttimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    private CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);

                    cdt = new CountDownTimer(10000, 50) {
                        public void onTick(long millisUntilFinished) {
                            String strTime = String.format("%.0f"
                                    , (double) millisUntilFinished / 1000);
                            tv.setText(String.valueOf(strTime));
                        }

                        public void onFinish() {
                            cdt.start();
                        }
                    }.start();


    }

}
