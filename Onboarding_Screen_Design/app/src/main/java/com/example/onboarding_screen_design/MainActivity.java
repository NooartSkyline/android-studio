package com.example.onboarding_screen_design;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mslideViewPager;
    private LinearLayout dotsLayout;

    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mslideViewPager.findViewById(R.id.slide_ViewPager);
        dotsLayout.findViewById(R.id.dotsLayout);

        sliderAdapter = new SliderAdapter(MainActivity.this);
        mslideViewPager.setAdapter(sliderAdapter);
    }
}
//https://youtu.be/byLKoPgB7yA?t=711