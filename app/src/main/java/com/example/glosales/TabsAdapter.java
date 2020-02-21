package com.example.glosales;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


class TabsAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    TabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNumOfTabs = NoofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Login();
        } else {
            return new CreateAccount();
        }
    }
}
