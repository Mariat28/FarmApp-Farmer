package com.example.glosales.agromarket.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.glosales.agromarket.fragments.MyProducts;
import com.example.glosales.agromarket.fragments.Orders;


public class HomeTabsAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public HomeTabsAdapter(FragmentManager fm, int noofTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNumOfTabs = noofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return position == 1 ? new MyProducts() : new Orders();
    }
}
