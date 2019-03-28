package apps_dohomeapp.dohome.co.th.connectsqlserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.os.AsyncTask;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText txtUserName;
    Button btnLogin;
    TextView link_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtUserName= findViewById(R.id.txtUserName);
        link_signup= findViewById(R.id.link_signup);
        btnLogin =  findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                conultarpersona();
            }
        });


    }

    public  Connection conexionBD(){
        Connection cnn=null;
        try{
            StrictMode.ThreadPolicy politica = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(politica);

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
//            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.26.2;databaseName=BDTienda;user=sa;password=3124;");
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.0.104;databaseName=DBTRANS;user=abc;password=abc;");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return  cnn;
    }

    public void conultarpersona(){
        try {
            Statement stm = conexionBD().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM DBTRANS..TBTrans_Check_Price_Tag WHERE BRANCH ='" + txtUserName.getText().toString() + "'");

            if(rs.next()){
                link_signup.setText(rs.getString(2));


            }
            txtUserName.setText("");

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
