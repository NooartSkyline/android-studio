package th.co.dohome.apps_dohomeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import th.co.dohome.apps_dohomeapp.Fragment.Fragment_displaygeneral;

public class Main_manu extends AppCompatActivity {

    private ImageView iv_main_menu_product,iv_main_menu_stock,iv_main_menu_userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main_manu);

        iv_main_menu_product = findViewById(R.id.iv_mainm_menu_product);
        iv_main_menu_stock = findViewById(R.id.iv_mainm_menu_stock);
        iv_main_menu_userinfo = findViewById(R.id.iv_mainm_menu_userinfo);

        //set Image button
        iv_main_menu_product.setImageResource(R.drawable.menu_productdetail);
        iv_main_menu_stock.setImageResource(R.drawable.menu_stock);
        iv_main_menu_userinfo.setImageResource(R.drawable.menu_userinfo);


        iv_main_menu_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_main_menu_product.setImageResource(R.drawable.menu_productdetail2);

                Intent intent = new Intent(Main_manu.this, Fragment_displaygeneral.class);
                intent.putExtra("key","value");
                startActivity(intent);
            }
        });
        iv_main_menu_stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_main_menu_stock.setImageResource(R.drawable.menu_stock2);

                Intent intent = new Intent(Main_manu.this,Stock.class);
                intent.putExtra("key","value");
                startActivity(intent);
            }
        });
        iv_main_menu_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_main_menu_userinfo.setImageResource(R.drawable.menu_userinfo2);

                Intent intent = new Intent(Main_manu.this, Userinfo.class);
                intent.putExtra("key","value");
                startActivity(intent);
            }
        });
    }
}
