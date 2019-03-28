package com.example.loadurlimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView imv_test_url;
    String url = "https://travel.mthai.com/app/uploads/2018/05/15826514_644637349054316_8233087823371163157_n-1.jpg";
    public TextView tv_thead;

    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imv_test_url = findViewById(R.id.imv_test_url);
        tv_thead = findViewById(R.id.tv_thead);
        loadImageFromUrl(url);    }

    private void loadImageFromUrl(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imv_test_url, new com.squareup.picasso.Callback() {

                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
    }

}
