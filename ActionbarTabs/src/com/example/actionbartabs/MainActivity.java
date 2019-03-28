package com.example.actionbartabs;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {

	
	private MyFragmentPageAdapter mPageAdapter;
	private ViewPager mViewPager;

	private ActionBar ab;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// setup actionbar

		// get the actionbar
		ab = getSupportActionBar();

		// Specify that the Home/Up button should not be enabled, since there is no hierarchical
		// parent.
		ab.setHomeButtonEnabled(false);

		// Specify that we will be displaying tabs in the action bar.
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


		// This adapter will hold the fragments
		mPageAdapter = new MyFragmentPageAdapter(getSupportFragmentManager());

		// Get the view pager widget from the activity layout
		mViewPager = (ViewPager) findViewById(R.id.view_pager);

		// Set the page adapter to the view pager
		mViewPager.setAdapter(mPageAdapter);

		// This listener will be triggered when the user swipes between the pages
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {

				// Set the selected navigation item corresponding to the current position
				ab.setSelectedNavigationItem(position);
			}
		});

		// Iterate the page adapter and add the fragments to the view pager
		for (int i = 0; i < mPageAdapter.getCount(); i++) {

			// Create a new tab
			Tab newTab = ab.newTab();
			// Set tab title
			newTab.setText(mPageAdapter.getPageTitle(i));
			// Set tab listener
			newTab.setTabListener(new MyTabListener(mViewPager));
			// Add the tab to the actionbar
			ab.addTab(newTab);
		}
	}
	
}
