package com.example.properties_network;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txt_ip;
    private TextView tv_get_ip,tv_MacAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_ip = findViewById(R.id.txt_ip);
        tv_get_ip = findViewById(R.id.tv_get_ip);
        tv_MacAddress = findViewById(R.id.tv_MacAddress);



        tv_get_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_ip.setText(getIpAddress(getApplicationContext()));
            }
        });
        tv_MacAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_ip.setText(getMacAddress(getApplicationContext()));
            }
        });
    }

    private String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getMacAddress();
    }
    private String getIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return Formatter.formatIpAddress(wifiInfo.getIpAddress());
    }
    private String getBSSID(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getBSSID();
    }
}
