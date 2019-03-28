package cpe.kku.pong.csuav_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String keyid_man = "id_man";
    String id_man;
    private GoogleMap mMap;
    JSONArray getresult;
    private PolylineOptions mPolylineOptions;
    LatLng ucurrent;
    LatLng uavcurrent;
    LatLng home;
    LatLng taget;
    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map", "Satellite", "Terrain", "Hybrid"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        id_man = getIntent().getExtras().getString(keyid_man);
        runThread();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        initializeMap();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(13.736717, 100.523186), 16.0f));
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point) {
                // TODO Auto-generated method stub


                showMapTypeSelectorDialog();
            }
        });
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(),"Service is stop Show Map.",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }



    private JSONArray WriteDataDev(String val1)
    {
        try {
            String USER_AGENT = "Mozilla/5.0";
            String url = GBValue.hostname + "fetchdatadron_app.php?id_man="+val1;

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

            JSONObject json = new JSONObject(response.toString());
            JSONArray jArray = json.getJSONArray("management");

            return jArray;



        } catch (Exception e) {
            return null;
        }
    }



    private void runThread() {

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                while (true) {
                    getresult = WriteDataDev(id_man);
                    while (getresult == null) {
                        ;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            try {
                                if (getresult != null) {
                                    if (getresult.length() > 0) {
                                        JSONObject json_data = getresult.getJSONObject(0);
                                        //String id_man = json_data.getString("id_man");
                                        double lath = json_data.getDouble("lath");
                                        double lonh = json_data.getDouble("lonh");
                                        double latc = json_data.getDouble("latc");
                                        double lonc = json_data.getDouble("lonc");
                                        double latw = json_data.getDouble("latw");
                                        double lonw = json_data.getDouble("lonw");
                                        double ulatitude = 0;
                                        double ulongitude = 0;
                                        GPSTracker gps = new GPSTracker(getApplication());
                                        if (gps.canGetLocation()) {

                                            ulatitude = gps.getLatitude();
                                            ulongitude = gps.getLongitude();
                                        }
                                        // Add a marker in Sydney and move the camera
                                        ucurrent = new LatLng(ulatitude, ulongitude);
                                        uavcurrent = new LatLng(latc, lonc);
                                        home = new LatLng(lath, lonh);
                                        taget = new LatLng(latw, lonw);
                                        updatePolyline();
                                        updateMarker();
                                        updateCamera();
                                        getresult = null;

                                    }
                                }
                            } catch (JSONException ex) {

                            }
                        }
                    });

                }
            }
        }.start();
    }

    private void initializeMap() {
        mPolylineOptions = new PolylineOptions();
        mPolylineOptions.color(Color.BLUE).width(10);
    }
    private void updatePolyline() {
        mMap.clear();
        mMap.addPolyline(mPolylineOptions.add(uavcurrent));

    }
    private void updateMarker() {
        mMap.addMarker(new MarkerOptions().position(ucurrent).title("The position of yours.").snippet(ucurrent.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.youlocal)));
        mMap.addMarker(new MarkerOptions().position(uavcurrent).title("UAV Current Location").snippet(uavcurrent.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mdrone)));
        mMap.addMarker(new MarkerOptions().position(home).title("Home Location").snippet(home.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mhome)));
        mMap.addMarker(new MarkerOptions().position(taget).title("Taget Location").snippet(taget.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mtarget)));
    }
    private void updateCamera() {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(uavcurrent));
    }

    private void showMapTypeSelectorDialog() {
        // Prepare the dialog by setting up a Builder.
        final String fDialogTitle = "Select Map Type";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(fDialogTitle);

        // Find the current map type to pre-check the item representing the current state.
        int checkItem = mMap.getMapType() - 1;

        // Add an OnClickListener to the dialog, so that the selection will be handled.
        builder.setSingleChoiceItems(
                MAP_TYPE_ITEMS,
                checkItem,
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        // Locally create a finalised object.

                        // Perform an action depending on which item was selected.
                        switch (item) {
                            case 0:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                break;
                            case 1:
                                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case 2:
                                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;
                            case 3:
                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                            default:
                                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }
                        dialog.dismiss();
                    }
                }
        );

        // Build the dialog and show it.
        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.setCanceledOnTouchOutside(true);
        fMapTypeDialog.show();
    }


}
