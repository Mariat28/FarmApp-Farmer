package ug.global.glofarmedited.agromarket.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import ug.global.glofarmedited.agromarket.fragments.AnimalSupplies;
import ug.global.glofarmedited.agromarket.fragments.PlantSupplies;

public class SuppliesTabsAdapter extends FragmentStatePagerAdapter {
    private int nooftabs;

    public SuppliesTabsAdapter(@NonNull FragmentManager fm, int nooftabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.nooftabs = nooftabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlantSupplies();
        } else {
            return new AnimalSupplies();
        }
    }

    @Override
    public int getCount() {
        return nooftabs;
    }
}
