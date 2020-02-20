package com.example.glosales.farmfinancials;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.glosales.R;
import com.example.glosales.farmfinancials.adapters.FarmFinancialTabsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class FarmFinancialsMain extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_financials_main);
        Toolbar toolbar = findViewById(R.id.financialstool_bar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        TabLayout tabLayout = findViewById(R.id.financialtab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Income"));
        tabLayout.addTab(tabLayout.newTab().setText("Inputs"));
        tabLayout.addTab(tabLayout.newTab().setText("Expenditures"));
        final ViewPager viewPager = findViewById(R.id.financialsview_pager);
        FarmFinancialTabsAdapter farmFinancialTabsAdapter = new FarmFinancialTabsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(farmFinancialTabsAdapter);
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
