package com.example.onboarding_screen_design;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context ) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.smurf1,
            R.drawable.smurf2,
            R.drawable.smurf3

    };
    public String[]slid_headings ={
            "smurf1",
            "smurf2",
            "smurf3"
    };
    public String[]slid_descs ={
            "smurf1smurf1smurf1smurf1smurf1smurf1smurf1",
            "smurf2smurf2smurf2smurf2smurf2smurf2smurf2smurf2smurf2smurf2",
            "smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3smurf3"
    };
    @Override
    public int getCount() {
        return slid_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layuot,container,false);

        ImageView slideimv = view.findViewById(R.id.imv_slide);
        TextView tv_slidehead = view.findViewById(R.id.tv_slidehead);
        TextView tv_slidedesc = view.findViewById(R.id.tv_slidedesc);

        slideimv.setImageResource(slide_images[position]);
        tv_slidehead.setText(slid_headings[position]);
        tv_slidedesc.setText(slid_descs[position]);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

//    https://www.youtube.com/watch?v=byLKoPgB7yA
}
