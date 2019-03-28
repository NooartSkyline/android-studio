package com.example.actionbartabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * FragmentPagetAdapter returns the current selected fragment
 * @author vinothkumar.a
 *
 */
public class MyFragmentPageAdapter extends FragmentPagerAdapter {

	// Total tabs count
	private static final int PAGE_COUNT = 3;
	
	public MyFragmentPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		switch (i) {
		case 0:
			// Display the main fragment
			return new MainFragment();

		case 1:
			// Display second fragment
			Fragment fragment = new SecondFragment();
			// Pass extra values using bundle extras as arguments.
			Bundle args = new Bundle();
			args.putString("EXTRAS", "This data is passed as bundle argument");
			fragment.setArguments(args);
			return fragment;
		case 2:
			// Display second fragment
			Fragment fragment3 = new ThirdFragment();
			return fragment3;

		default:
			return new MainFragment();
		}
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// returns the page title for the current tab
		return "Tab Title " + (position + 1);
	}
}
