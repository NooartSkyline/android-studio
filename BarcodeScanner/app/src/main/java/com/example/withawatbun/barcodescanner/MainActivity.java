package com.example.withawatbun.barcodescanner;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zebra.adc.decoder.Barcode2DWithSoft;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Thread thread;
    TextView tv_barcode;
    EditText editText, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9
            , editText10, editText11, editText12, editText13, editText14, editText15, editText16, editText17, editText18, editText19;
    Barcode2DWithSoft mReader;
    boolean threadStop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_barcode = findViewById(R.id.tv_barcode);
        editText = findViewById(R.id.ed_input);
        editText2 = findViewById(R.id.ed_input2);
        editText3 = findViewById(R.id.ed_input3);
        editText4 = findViewById(R.id.ed_input4);
        editText5 = findViewById(R.id.ed_input5);
        editText6 = findViewById(R.id.ed_input6);
        editText7 = findViewById(R.id.ed_input7);
        editText8 = findViewById(R.id.ed_input8);
        editText9 = findViewById(R.id.ed_input9);
        editText10 = findViewById(R.id.ed_input10);
        editText11 = findViewById(R.id.ed_input11);
        editText12 = findViewById(R.id.ed_input12);
        editText13 = findViewById(R.id.ed_input13);
        editText14 = findViewById(R.id.ed_input14);
        editText15 = findViewById(R.id.ed_input15);
        editText16 = findViewById(R.id.ed_input16);
        editText17 = findViewById(R.id.ed_input17);
        editText18 = findViewById(R.id.ed_input18);
        editText19 = findViewById(R.id.ed_input19);

        if (Build.MODEL.equalsIgnoreCase("C4050-Q4") && Build.DEVICE.equalsIgnoreCase("C4050-Q4")) {
            try {
                mReader = Barcode2DWithSoft.getInstance();
                new InitTaskScanner().execute();
            } catch (Exception ex) {
                Log.e("getInstanceError", ex.toString());
                return;
            }
        } else {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == 139) {
            if (event.getRepeatCount() == 0) {
                doDecode();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 139) {
            mReader.stopScan();
        }
        return super.onKeyUp(keyCode, event);
    }

    public class InitTaskScanner extends AsyncTask<String, Integer, Boolean> {
        ProgressDialog progressDialog;

        @Override
        protected Boolean doInBackground(String... strings) {
            Boolean result = false;

            if (mReader != null) {
                result = mReader.open(MainActivity.this);
                if (result) {
                    mReader.setParameter(324, 1);
                    mReader.setParameter(300, 0);
                    mReader.setParameter(361, 0);
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            progressDialog.cancel();
            if (mReader != null) {
                mReader.setScanCallback(doc_scan);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Loading....");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

        public Barcode2DWithSoft.ScanCallback doc_scan = new Barcode2DWithSoft.ScanCallback() {
            @Override
            public void onScanComplete(int i, int i1, byte[] data) {

                if (data != null && data.length != 0) {

                    String doc = new String(data).trim();

                    if (editText.isFocused()) {
                        editText.setText(doc);
                    } else if (editText2.isFocused()) {
                        editText2.setText(doc);
                    } else if (editText3.isFocused()) {
                        editText3.setText(doc);
                    } else if (editText4.isFocused()) {
                        editText4.setText(doc);
                    } else if (editText5.isFocused()) {
                        editText5.setText(doc);
                    } else if (editText6.isFocused()) {
                        editText6.setText(doc);
                    } else if (editText7.isFocused()) {
                        editText7.setText(doc);
                    } else if (editText8.isFocused()) {
                        editText8.setText(doc);
                    } else if (editText9.isFocused()) {
                        editText9.setText(doc);
                    }else if (editText10.isFocused()) {
                        editText10.setText(doc);
                    } else if (editText11.isFocused()) {
                        editText11.setText(doc);
                    } else if (editText12.isFocused()) {
                        editText12.setText(doc);
                    } else if (editText13.isFocused()) {
                        editText13.setText(doc);
                    } else if (editText14.isFocused()) {
                        editText14.setText(doc);
                    } else if (editText15.isFocused()) {
                        editText15.setText(doc);
                    } else if (editText16.isFocused()) {
                        editText16.setText(doc);
                    } else if (editText17.isFocused()) {
                        editText17.setText(doc);
                    }else if (editText18.isFocused()) {
                        editText18.setText(doc);
                    } else if (editText19.isFocused()) {
                        editText19.setText(doc);
                    }
                }

            }
        };

        private void doDecode() {
            if (threadStop) {
                boolean Continuous = false;
                int Between = 0;
                thread = new DecodeThread(Continuous, Between);
                thread.start();
            }
            if (mReader != null) {

                mReader.stopScan();
                if (tv_barcode.isFocused()) {
                    mReader.setScanCallback(doc_scan);
                }
            }
        }

        private class DecodeThread extends Thread {
            private boolean isContinuous = false;
            private long sleepTime = 1000;

            public DecodeThread(boolean isContinuous, int sleep) {
                this.isContinuous = isContinuous;
                this.sleepTime = sleep;
            }

            @Override
            public void run() {
                super.run();
                do {
                    mReader.scan();

                    if (isContinuous) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } while (isContinuous && !threadStop);
            }
        }
}
