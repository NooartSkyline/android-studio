package cpe.kku.pong.csuav_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsMylocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng ucurrent;
    private static final CharSequence[] MAP_TYPE_ITEMS =
            {"Road Map", "Satellite", "Terrain", "Hybrid"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_mylocation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        double ulatitude = 0;
        double ulongitude = 0;
        GPSTracker gps = new GPSTracker(getApplication());
        if (gps.canGetLocation()) {

            ulatitude = gps.getLatitude();
            ulongitude = gps.getLongitude();
        }
        ucurrent = new LatLng(ulatitude, ulongitude);
        mMap.addMarker(new MarkerOptions().position(ucurrent).title("The position of yours.").snippet(ucurrent.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.youlocal)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ucurrent, 16.0f));



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

    private void runThread() {

        new Thread() {
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            double ulatitude = 0;
                            double ulongitude = 0;
                            GPSTracker gps = new GPSTracker(getApplication());
                            if (gps.canGetLocation()) {

                                ulatitude = gps.getLatitude();
                                ulongitude = gps.getLongitude();
                            }
                            mMap.clear();
                            ucurrent = new LatLng(ulatitude, ulongitude);
                            mMap.addMarker(new MarkerOptions().position(ucurrent).title("The position of yours.").snippet(ucurrent.toString())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.youlocal)));
                        }
                    });

                }
            }
        }.start();
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
