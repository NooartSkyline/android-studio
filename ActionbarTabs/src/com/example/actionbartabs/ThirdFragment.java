package com.example.actionbartabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	 View view = inflater.inflate(R.layout.fragment_three, container, false);
         ((TextView) view.findViewById(android.R.id.text1)).setText("Third Fragment");
         return view;
    }
}
