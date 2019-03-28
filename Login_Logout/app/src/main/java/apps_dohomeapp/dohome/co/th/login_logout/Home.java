package apps_dohomeapp.dohome.co.th.login_logout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    private SharedPreferences getsharedPre;
    private String user,pass, getuser_pre, getpass_pre;
    private SharedPreferences.Editor editor;
    private Button btn_Logout;
    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tv_test = findViewById(R.id.tv_test);
        btn_Logout = findViewById(R.id.btn_Logout);

        Bundle bundle = getIntent().getExtras();
        user = bundle.getString("user");
        pass = bundle.getString("pass");

        //เขียน SharedPreferences
        editor = getSharedPreferences("SharedPre", MODE_PRIVATE).edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.commit();

        getsharedPre = getSharedPreferences("SharedPre", MODE_PRIVATE);
        getuser_pre = getsharedPre.getString("user", null);
        getpass_pre = getsharedPre.getString("pass", null);

        tv_test.setText(getuser_pre + getpass_pre);

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor = getSharedPreferences("SharedPre", MODE_PRIVATE).edit();
                editor.remove("user");
                editor.remove("pass");
                editor.commit();

                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
