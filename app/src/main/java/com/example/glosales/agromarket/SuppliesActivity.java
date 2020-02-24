package com.example.glosales.agromarket;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapters.SuppliesTabsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class SuppliesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplies);
        Toolbar toolbar = findViewById(R.id.suppliestool_bar);
        toolbar.setTitle("Glo Store");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TabLayout tabLayout = findViewById(R.id.suppliestab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Plant Supplies"));
        tabLayout.addTab(tabLayout.newTab().setText("Animal Supplies"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.suppliesview_pager);
        SuppliesTabsAdapter tabsAdapter = new SuppliesTabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}