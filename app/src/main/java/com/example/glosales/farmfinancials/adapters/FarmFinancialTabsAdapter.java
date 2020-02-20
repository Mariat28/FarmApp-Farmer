package com.example.glosales.farmfinancials.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.glosales.farmfinancials.Expenses;
import com.example.glosales.farmfinancials.InputsFragment;
import com.example.glosales.farmfinancials.StockDetails;

public class FarmFinancialTabsAdapter extends FragmentStatePagerAdapter {
    private int mNooftabs;

    public FarmFinancialTabsAdapter(@NonNull FragmentManager fm, int Nooftabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.mNooftabs = Nooftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new StockDetails();
        } else if (position == 1) {
            return new InputsFragment();
        } else {
            return new Expenses();
        }

    }

    @Override
    public int getCount() {
        return mNooftabs;
    }
}
