package apps_dohomeapp.dohome.co.th.check_connected_wifi;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv_wifi_info,tv_getSubtype,tv_getSubtypeName,tv_getExtraInfo,tv_getDetailedState,tv_getState,tv_getType,tv_getTypeName,tv_getReason,tv_ipAddress;
    private String wifi_info,getSubtype,getSubtypeName,getExtraInfo,getDetailedState,getState,getType,getTypeName,getReason;
    private String ipAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_wifi_info = findViewById(R.id.tv_wifi_info);
        tv_getSubtype = findViewById(R.id.tv_getSubtype);
        tv_getSubtypeName = findViewById(R.id.tv_getSubtypeName);
        tv_getExtraInfo = findViewById(R.id.tv_getExtraInfo);
        tv_getDetailedState = findViewById(R.id.tv_getDetailedState);
        tv_getState = findViewById(R.id.tv_getState);
        tv_getType = findViewById(R.id.tv_getType);
        tv_getTypeName = findViewById(R.id.tv_getTypeName);
        tv_getReason = findViewById(R.id.tv_getReason);
        tv_ipAddress = findViewById(R.id.tv_ipAddress);

        ConnectivityManager connManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        wifi_info = String.valueOf(mWifi.isConnected());
        getSubtype = String.valueOf(mWifi.getSubtype());
        getSubtypeName = String.valueOf(mWifi.getSubtypeName());
        getExtraInfo = String.valueOf(mWifi.getExtraInfo());
        getDetailedState = String.valueOf(mWifi.getDetailedState());
        getState = String.valueOf(mWifi.getState());
        getType = String.valueOf(mWifi.getType());
        getTypeName = String.valueOf(mWifi.getTypeName());
        getReason = String.valueOf(mWifi.getReason());

        //getIpAddress
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

        //getMacAddress
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String MacAddress = wInfo.getMacAddress();


        tv_getSubtype.setText("getSubtype : " + getSubtype);
        tv_getSubtypeName.setText("getSubtypeName : " + getSubtypeName);
        tv_getExtraInfo.setText("getExtraInfo : " + getExtraInfo);
        tv_getDetailedState.setText("getDetailedState : " + getDetailedState);
        tv_getState.setText("getState : " + getState);
        tv_getType.setText("getType : " + getType);
        tv_getTypeName.setText("getTypeName : " + getTypeName);
        tv_getReason.setText("getReason : " + getReason);
        tv_ipAddress.setText("ipAddress : " + ipAddress + "  MacAddress : " + MacAddress);

        if (mWifi.isConnected()) {
            // Do whatever
            tv_wifi_info.setText("wifi_info : " + wifi_info);
        }else {
            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//            startActivity(new Intent(Settings.ACTION_DEVICE_INFO_SETTINGS));
            wifi_info = String.valueOf(mWifi.isConnected());
            tv_wifi_info.setText("wifi_info : " + wifi_info);
        }
    }
}
