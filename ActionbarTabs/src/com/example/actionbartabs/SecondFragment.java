package com.example.actionbartabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	 View view = inflater.inflate(R.layout.fragment_two, container, false);
         Bundle args = getArguments();
         ((TextView) view.findViewById(android.R.id.text1)).setText(args.getString("EXTRAS"));
         return view;
    }
}
