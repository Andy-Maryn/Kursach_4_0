package com.example.kursach_4_0.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.kursach_4_0.fragment.FirstFragment;
import com.example.kursach_4_0.fragment.SecondFragment;
import com.google.android.material.tabs.TabLayout;
public class PackageTabAdapter extends FragmentStatePagerAdapter {
    TabLayout tabLayout;
    public PackageTabAdapter(FragmentManager fm, TabLayout _tabLayout) {
        super(fm);
        this.tabLayout = _tabLayout;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new FirstFragment();
        }
        else if (position == 1)
        {
            fragment = new SecondFragment();
        }
        return fragment;
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Города Украины";
        }
        else if (position == 1)
        {
            title = "Мои города";
        }
        return title;
    }
}
