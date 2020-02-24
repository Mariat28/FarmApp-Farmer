package com.example.glosales.agromarket.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.glosales.agromarket.fragments.AnimalSupplies;
import com.example.glosales.agromarket.fragments.Market;
import com.example.glosales.agromarket.fragments.PlantSupplies;

public class SuppliesTabsAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public SuppliesTabsAdapter(FragmentManager fm, int noofTabs) {
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
        if (position == 1) {
            return new AnimalSupplies();

        } else if (position == 2) {
            return new Market();

        } else {
            return new PlantSupplies();
        }

    }

}


