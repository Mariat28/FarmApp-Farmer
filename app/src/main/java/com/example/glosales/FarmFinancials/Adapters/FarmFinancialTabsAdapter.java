package com.example.glosales.FarmFinancials.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.glosales.AgroMarket.Fragments.Stock_Details;
import com.example.glosales.FarmFinancials.Expenses;
import com.example.glosales.FarmFinancials.InputsFragment;

public class FarmFinancialTabsAdapter extends FragmentStatePagerAdapter {
    int mNooftabs;

    public FarmFinancialTabsAdapter(@NonNull FragmentManager fm, int Nooftabs) {
        super(fm);
        this.mNooftabs = Nooftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Stock_Details stock_details = new Stock_Details();
                return stock_details;
            case 1:
                InputsFragment inputs = new InputsFragment();
                return inputs;

            case 2:
                Expenses expenses = new Expenses();
                return expenses;

        }


        return null;
    }

    @Override
    public int getCount() {
        return mNooftabs;
    }
}
