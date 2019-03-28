package com.example.withawatbun.mymap;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public double lat, lon;

    private CountDownTimer mCountDownTimer;
    private static final long START_TIME_IN_MILLIS = 5000;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private boolean mTimerRunning;
    int seconds;
    LatLng My_Location;
    SupportMapFragment mapFragment;
    private static final CharSequence[] MAP_TYPE_ITEMS = {"Road Map", "Satellite", "Terrain", "Hybrid"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this); //เรียกฟังชั่น onMapReady
        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        Count();
        ArtClick();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        My_Location = new LatLng(lat, lon); // ดึงมาใส่ได้
//        My_Location = new LatLng(15.312, 104.870); // ดึงมาใส่ได้

        mMap.addMarker(new MarkerOptions().position(My_Location).title("My Lacation").snippet(My_Location.toString())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bpoint)));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(My_Location, 19.0f));  //Zoom

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);//ดาวเทียม
//        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
//        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                showMapTypeSelectorDialog();
            }
        });
        }


    public void ArtClick() { //get GPS จาก เครื่องตัวเอง
        GpsTracker gt = new GpsTracker(getApplicationContext());
        Location l = gt.getLocation();
        if (l == null) {
            Toast.makeText(getApplicationContext(), "GPS unable to get Value", Toast.LENGTH_SHORT).show();
        } else {
            lat = l.getLatitude();
            lon = l.getLongitude();

//            Toast.makeText(getApplicationContext(),"GPS Lat = "+lat+"\n long = "+lon,Toast.LENGTH_SHORT).show();
            mapFragment.getMapAsync(this);
        }
    }

    //    -----------------------------นับเวลา--------------------------------------
    public void Count() {

        if (mTimerRunning) {
            pauseTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
                mTimeLeftInMillis = START_TIME_IN_MILLIS;
            }

            @Override
            public void onFinish() {
                startTimer();
            }
        }.start();
        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

        }
    }

    private void updateCountDownText() {
        seconds = (int) (mTimeLeftInMillis / 1000);

        if (seconds == 1) {
//            Toast.makeText(this, String.valueOf(seconds), Toast.LENGTH_SHORT).show();
            ArtClick();
        }
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
