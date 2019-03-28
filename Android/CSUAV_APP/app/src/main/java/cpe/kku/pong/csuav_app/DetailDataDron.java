package cpe.kku.pong.csuav_app;


import android.app.ProgressDialog;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailDataDron extends Fragment {

    final int handlerState = 0;
    JSONArray getresult;
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11;
    private ConnectedThread mConnectedThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                JSONObject json_data = getresult.getJSONObject(0);
                String name = json_data.getString("name");
                String cd_uav = json_data.getString("cd_uav");
                String uav_work = json_data.getString("uav_work");
                String sta = json_data.getString("sta");
                String oth1 = json_data.getString("oth1");
                String oth2 = json_data.getString("oth2");
                String latcu = json_data.getString("latcu");
                String loncu = json_data.getString("loncu");
                String hgnd = json_data.getString("hgnd");
                String pwr = json_data.getString("pwr");
                String gpsc = json_data.getString("gpsc");

                textView1.setText(name);
                textView2.setText(cd_uav);
                if (uav_work.equals("0")) {
                    textView3.setText("เตรียมพร้อมทำงาน");
                } else {
                    textView3.setText("กำลังบินใช้งาน");
                }
                if (sta.equals("0")) {
                    textView4.setText("เครื่องปิด");
                } else {
                    textView4.setText("เครื่องเปิด");
                }
                textView5.setText(oth1);
                textView6.setText(oth2);
                textView7.setText(pwr);
                textView8.setText(gpsc);
                textView9.setText(hgnd);
                textView10.setText(latcu);
                textView11.setText(loncu);
            }catch (JSONException ex) {
            }
        }
    };


    public DetailDataDron() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_data_dron, container, false);
        textView1 = (TextView)v.findViewById(R.id.datacol1);
        textView2 = (TextView)v.findViewById(R.id.datacol2);
        textView3 = (TextView)v.findViewById(R.id.datacol3);
        textView4 = (TextView)v.findViewById(R.id.datacol4);
        textView5 = (TextView)v.findViewById(R.id.datacol5);
        textView6 = (TextView)v.findViewById(R.id.datacol6);
        textView7 = (TextView)v.findViewById(R.id.datacol7);
        textView8 = (TextView)v.findViewById(R.id.datacol8);
        textView9 = (TextView)v.findViewById(R.id.datacol9);
        textView10 = (TextView)v.findViewById(R.id.datacol10);
        textView11 = (TextView)v.findViewById(R.id.datacol11);
        mConnectedThread = new ConnectedThread();
        mConnectedThread.start();
        return v;
    }


    JSONArray WriteDataDev(String val1)
    {
        try {

            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "fetchdatadron_2.php?id_uav="+val1;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONArray jArray = new JSONArray(response.toString());

            return jArray;


        } catch (Exception e) {
            return null;
        }
    }

    private class ConnectedThread extends Thread {
        String id_uav;
        boolean shouldContinue;

        //creation of the connect thread
        public ConnectedThread() {
            id_uav = GBValue.id_uav;
            shouldContinue = true;
        }


        public void run() {
            // Keep looping to listen for received messages
            while (shouldContinue) {

                getresult = WriteDataDev(id_uav);
                if (getresult != null) {
                    handler.sendEmptyMessage(0);
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        public void cancel() {
            shouldContinue = false;
        }
    }

    @Override
    public void onDestroyView() {
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        if (mConnectedThread != null) {
            mConnectedThread.cancel();
            mConnectedThread = null;
        }
        Toast.makeText(getContext(),"Stop Connected.",Toast.LENGTH_SHORT).show();
        super.onDestroyView();
    }

}
