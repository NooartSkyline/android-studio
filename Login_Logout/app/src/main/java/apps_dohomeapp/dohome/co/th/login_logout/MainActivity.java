package apps_dohomeapp.dohome.co.th.login_logout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private SharedPreferences.Editor editor;
    private SharedPreferences getsharedPre;
    private String user,pass, getuser_pre,getpass_pre;
    private EditText ed_user,ed_pass;
    private Button btn_Logon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_user = findViewById(R.id.ed_user);
        ed_pass = findViewById(R.id.ed_pass);
        btn_Logon = findViewById(R.id.btn_Logon);

        getsharedPre = getSharedPreferences("SharedPre", MODE_PRIVATE);
        getuser_pre = getsharedPre.getString("user", null);
        getpass_pre = getsharedPre.getString("pass", null);

        if(getuser_pre != null){
            Intent intent = new Intent(MainActivity.this,Home.class);
            intent.putExtra("user",getuser_pre);
            intent.putExtra("pass",getpass_pre);
            startActivity(intent);
        }

        btn_Logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = ed_user.getText().length();
                int b = ed_pass.getText().length();

                if(a <= 0 || b <= 0){
                    if(a <= 0) {
                        ed_user.requestFocus();
                        ed_user.setError("กรอก user");
                    }else {
                     ed_pass.requestFocus();
                     ed_pass.setError("กรอก password");
                    }
                }else {
                   user = ed_user.getText().toString();
                   pass = ed_pass.getText().toString();

                    Intent intent = new Intent(MainActivity.this,Home.class);
                    intent.putExtra("user",user);
                    intent.putExtra("pass",pass);
                    startActivity(intent);

                }
                

            }
        });
    }
}
