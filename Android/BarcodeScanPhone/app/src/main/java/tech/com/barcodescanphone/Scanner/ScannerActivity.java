package tech.com.barcodescanphone.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import tech.com.barcodescanphone.R;

import static tech.com.barcodescanphone.MainActivity.CONTENT;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;

    int status_flash_off = 0, status_flash_on = 1,temp ;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
////            case R.id.turn_on_flash:{
////
////                temp = status_flash_on;
////                if(status_flash_on==1) {
////                    status_flash_on =status_flash_off;
////                    status_flash_off = temp;
////                    scannerView.setFlash(true);
////                }else{
////                    temp = status_flash_on;
////                    status_flash_on =status_flash_off;
////                    status_flash_off = temp;
////                    scannerView.setFlash(false);
////                }
//            }
//        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_scanner);
        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);

        scannerView = new ZXingScannerView(this);
        scannerView.setAutoFocus(true);
        contentFrame.addView(scannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        //Call back data to main activity
        Intent intent = new Intent();
        intent.putExtra(CONTENT, rawResult.getText());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}