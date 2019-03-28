package apps_dohomeapp.dohome.co.th.test_inflate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private static final int REQUEST_MAIN = 11;
    private static final String VALUE_KEY = "value";

    private Button btnSubmit;
    private EditText edtValue;

    private String valueEditText = "asd";

    private TextView tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv_2 = findViewById(R.id.tv_2);

        tv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("a",valueEditText);
                setResult(1212,intent);
                finish();
            }
        });
    }
}
