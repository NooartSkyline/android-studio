package com.example.withawatbun.test_control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdepter());
    }

    public class MyAdepter extends RecyclerView.Adapter<viewholder>{

        @Override
        public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = (View) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_card,parent,false);

            viewholder viewholder = new viewholder(v);

            return viewholder;
        }

        @Override
        public void onBindViewHolder(viewholder holder, int position) {

            holder.txtView1.setText("vittawat");
            holder.txtView2.setText("Skyline");
            holder.txtView3.setText("Boonya");

            holder.imageView.setImageDrawable(getResources().getDrawable(R.drawable.a));

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    public class viewholder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtView1,txtView2,txtView3;

        public viewholder(View itemView) {
            super(itemView);

            txtView1 = (TextView)itemView.findViewById(R.id.textView);
            txtView2 = (TextView)itemView.findViewById(R.id.textView2);
            txtView3 = (TextView)itemView.findViewById(R.id.textView3);

            imageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}