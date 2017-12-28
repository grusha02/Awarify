package com.example.grusha.aawify;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by GRUSHA on 25-12-2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "All", "Local" };

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PapersFragment();
        } else {
            return new SettingsFragment();}
    }

    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
