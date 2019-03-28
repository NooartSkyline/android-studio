package com.example.withawatbun.navigation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {
    AHBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigation = (AHBottomNavigation) findViewById(R.id.myNavigation_ID);
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position ==0){
                    Bundle bundle = new Bundle();
                    bundle.putString("","");
                    Fragment1 home = new Fragment1();
                    home.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id,home).commit();

                    bottomNavigation.setAccentColor(Color.parseColor("#ff9000"));
                    return  true;

                }else if(position==1){
                    Fragment2 search = new Fragment2();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id,search).commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#04AEDA"));
                    return  true;

                }else if(position==2){
                    Fragment3 search = new Fragment3();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id,search).commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#04AEDA"));
                    return  true;

                }else if(position==3){
                    Fragment4 search = new Fragment4();
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_id,search).commit();
                    bottomNavigation.setAccentColor(Color.parseColor("#04AEDA"));
                    return  true;

                }
                return wasSelected;
            }
        });
        createNavItem();
    }
    private void createNavItem(){
        //Create Item
        AHBottomNavigationItem home = new AHBottomNavigationItem(getResources().getString(R.string.bottombar_main), R.drawable.ic_account_balance_black_24dp);
        AHBottomNavigationItem search = new AHBottomNavigationItem(getResources().getString(R.string.bottombar_search), R.drawable.search);
        AHBottomNavigationItem trans = new AHBottomNavigationItem(getResources().getString(R.string.bottombar_trans), R.drawable.ic_format_list_bulleted_black_24dp);
        AHBottomNavigationItem settingItem = new AHBottomNavigationItem(getResources().getString(R.string.bottombar_setting), R.drawable.setting);

        //Add Item

        bottomNavigation.addItem(home);
        bottomNavigation.addItem(search);
        bottomNavigation.addItem(trans);
        bottomNavigation.addItem(settingItem);

        //Set currect Item
        bottomNavigation.setAccentColor(Color.parseColor("#ff8c00"));
        bottomNavigation.setCurrentItem(0);

        Fragment1 home_fagment = new Fragment1();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_id,home_fagment).commit();

    }
}
