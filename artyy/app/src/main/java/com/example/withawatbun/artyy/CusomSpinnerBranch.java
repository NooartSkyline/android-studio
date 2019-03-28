package com.example.withawatbun.artyy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wutthiphong.thu on 23/12/2560.
 */

public class CusomSpinnerBranch extends BaseAdapter {


    ArrayList<BranchItem> list;
    Context context;
    LayoutInflater inflater;

    public CusomSpinnerBranch(Context context, ArrayList<BranchItem> list ) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        if(list!=null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.itemlaout, null);
        TextView txt_CODE =  convertView.findViewById(R.id.txt_CODE);
        TextView txt_OLD_CODE =   convertView.findViewById(R.id.txt_OLD_CODE);
        TextView txt_MYNAME =   convertView.findViewById(R.id.txt_MYNAME);
        TextView txt_STATUS =   convertView.findViewById(R.id.txt_STATUS);

        txt_CODE.setText(list.get(position).CODE);
        txt_OLD_CODE.setText(list.get(position).MYNAME);

        return convertView;
    }
}
