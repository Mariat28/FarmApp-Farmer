package com.example.glosales.AgroMarket.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.glosales.AgroMarket.Fragments.Market;
import com.example.glosales.AgroMarket.Fragments.MyProducts;
import com.example.glosales.AgroMarket.Fragments.Orders;


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
                Orders orders = new Orders();
                return orders;

            case 1:
                Market market;
                market = new Market();
                return market;

            case 2:
                MyProducts myProducts;
                myProducts = new MyProducts();
                return myProducts;


            default:
                return null;
        }
    }
}
