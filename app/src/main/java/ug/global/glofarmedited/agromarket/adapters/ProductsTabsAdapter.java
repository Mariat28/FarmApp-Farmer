package ug.global.glofarmedited.agromarket.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ug.global.glofarmedited.agromarket.fragments.Orders;
import ug.global.glofarmedited.agromarket.fragments.Products;

public class ProductsTabsAdapter extends FragmentStatePagerAdapter {
    private int nooftabs;

    public ProductsTabsAdapter(@NonNull FragmentManager fm, int nooftabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.nooftabs = nooftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return position == 1 ? new Products() : new Orders();
    }

    @Override
    public int getCount() {
        return nooftabs;
    }
}
