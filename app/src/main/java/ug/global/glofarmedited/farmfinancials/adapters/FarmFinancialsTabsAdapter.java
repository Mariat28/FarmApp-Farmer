package ug.global.glofarmedited.farmfinancials.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ug.global.glofarmedited.farmfinancials.fragments.Expenses;
import ug.global.glofarmedited.farmfinancials.fragments.Inputs;
import ug.global.glofarmedited.farmfinancials.fragments.Sales;

public class FarmFinancialsTabsAdapter extends FragmentStatePagerAdapter {
    private int nooftabs;

    public FarmFinancialsTabsAdapter(@NonNull FragmentManager fm, int nooftabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.nooftabs = nooftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Sales();
        } else if (position == 1) {
            return new Inputs();
        } else {
            return new Expenses();
        }
    }

    @Override
    public int getCount() {
        return nooftabs;
    }
}
