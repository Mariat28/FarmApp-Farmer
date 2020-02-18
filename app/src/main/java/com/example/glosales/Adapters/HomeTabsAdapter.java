package com.example.glosales.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.glosales.Fragments.Expenses;
import com.example.glosales.Fragments.Market;
import com.example.glosales.Fragments.Orders;
import com.example.glosales.Fragments.Stock_Details;

public class HomeTabsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public HomeTabsAdapter(FragmentManager fm, int NoofTabs) {
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Stock_Details stock_details = new Stock_Details();
                return stock_details;
            case 1:
                Orders orders = new Orders();
                return orders;

            case 2:
                Expenses expenses;
                expenses = new Expenses();
                return expenses;

            case 3:
                Market Market;
                Market = new Market();
                return Market;


            default:
                return null;
        }
    }
}
