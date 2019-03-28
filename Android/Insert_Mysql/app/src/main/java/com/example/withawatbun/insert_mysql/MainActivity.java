package com.example.withawatbun.insert_mysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editName,editLastName,editStatus;
    private static final String URL = "http://nooartskyline.000webhostapp.com/skyline/insert.php";
//    private static final String URL = "http://10.0.21.69:81/skyline/insert.php";
    private String name,lastname,status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        onBindView();


    }
    public void Btn_insert(View View){
        onEditText();
        onButtonClick();
    }
    private void onEditText(){
        name = editName.getText().toString();
        lastname = editLastName.getText().toString();
        status = editStatus.getText().toString();
    }
    private void onBindView(){
        editName = (EditText) findViewById(R.id.edt_name);
        editLastName = (EditText) findViewById(R.id.edt_lastname);
        editStatus = (EditText) findViewById(R.id.edt_status);
    }
    private void onButtonClick(){
        if(!name.isEmpty() && !lastname.isEmpty()){
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("onResponse", response);
                    editName.setText("");
                    editLastName.setText("");
                    editStatus.setText("");
                    Toast.makeText(MainActivity.this, "เพิ่มข้อมูลเรียบร้อย", Toast.LENGTH_SHORT).show();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError",error.toString());
                    Toast.makeText(MainActivity.this, "เกิดข้อผิดพลาดโปรลองอีกครั้ง", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name",name);
                    params.put("lastname",lastname);
                    params.put("status",status);
                    return params;

                }
            };
            requestQueue.add(request);
        }

    }
}
