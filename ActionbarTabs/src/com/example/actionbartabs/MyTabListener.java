package com.example.actionbartabs;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;

public class MyTabListener implements ActionBar.TabListener{

	private ViewPager mViewPager;
	
	public MyTabListener(ViewPager viewPager) {
		this.mViewPager = viewPager;
	}

	@Override
	public void onTabReselected(Tab tab,
			android.support.v4.app.FragmentTransaction arg1) {

	}

	@Override
	public void onTabSelected(Tab tab,
			android.support.v4.app.FragmentTransaction arg1) {
		// set the current item on tab selected
		mViewPager.setCurrentItem(tab.getPosition());		
	}

	@Override
	public void onTabUnselected(Tab tab,
			android.support.v4.app.FragmentTransaction arg1) {

	}
}
