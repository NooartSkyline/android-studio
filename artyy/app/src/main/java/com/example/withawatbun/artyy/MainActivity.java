package com.example.withawatbun.artyy;

import android.app.AlertDialog;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.json.JsonConverter;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1,spinner2,spinner3;
    private TextView textView,textView1;
    private ArrayList<String> m = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        createArray();

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,m);
        spinner1.setAdapter(adapter1);

        final String[] club = getResources().getStringArray(R.array.club);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, club);
        spinner2.setAdapter(adapter2);


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, m.get(position), Toast.LENGTH_SHORT).show();
                textView.setText(m.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "GGGGG", Toast.LENGTH_SHORT).show();

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, club[position], Toast.LENGTH_SHORT).show();
                textView1.setText(club[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "คุณไม่ได้เลือข้อมูล", Toast.LENGTH_SHORT).show();
            }
        });
        
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new GET_BRANCH().execute();

    }
    private void createArray(){

        m.add("art1");
        m.add("art2");
        m.add("art3");

    }

    private  class GET_BRANCH extends AsyncTask<Void,Void,ArrayList<BranchItem>> {
        AlertDialog mypDialog;
        private static final String NAMESPACE = "http://tempuri.org/";
        private String URL = "http://192.168.0.12/ServiceTransferDefacProductQAS240/ServiceTranferDefacQAS240.asmx";
        private static final String SOAP_ACTION = "http://tempuri.org/GET_BRANCH";
        private static final String METHOD_NAME = "GET_BRANCH";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mypDialog = new SpotsDialog(MainActivity.this);
            mypDialog.setCanceledOnTouchOutside(false);
            mypDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<BranchItem> whItems) {
            super.onPostExecute(whItems);
            CusomSpinnerBranch customAdapter = new CusomSpinnerBranch(MainActivity.this,whItems);
            spinner3.setAdapter(customAdapter);
            mypDialog.dismiss();
        }


        @Override
        protected ArrayList<BranchItem> doInBackground(Void... params) {
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL,120000);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapObject resultsRequestSOAP = (SoapObject) envelope.bodyIn;
                androidHttpTransport.getConnection().disconnect();
                JSONObject jsonObject = new JSONObject(resultsRequestSOAP.getProperty("GET_BRANCHResult").toString());
                return new JsonConverter<BranchItem>().toArrayList(jsonObject.getString("TBMaster_Branch").toString(),BranchItem.class) ;
            } catch (Exception e) {
                Log.e("GET_BRANCHError ", e.toString());
            }


            return null;
        }
    }
}
