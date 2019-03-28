package apps_dohomeapp.dohome.co.th.test_inflate;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_MAIN = 11;
    private static final String VALUE_KEY = "value";

    private Button btnGo;
    private TextView txtValue;
    private String valueFromSecondActivity = "";
    private TextView tv_1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_1 = findViewById(R.id.tv_1);

        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(intent, REQUEST_MAIN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_MAIN) {
            if (resultCode == 1212) {

                valueFromSecondActivity = data.getStringExtra("a");
                tv_1.setText(valueFromSecondActivity);

            } else if (resultCode == RESULT_CANCELED) {
                //TODO Handle Result Cancel
            }
        }
    }
}
