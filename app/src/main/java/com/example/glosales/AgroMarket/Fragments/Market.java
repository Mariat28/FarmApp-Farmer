package com.example.glosales.AgroMarket.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.AgroMarket.AdapterClasses.marketClass;
import com.example.glosales.AgroMarket.Adapters.MarketAdapter;
import com.example.glosales.R;

import java.util.ArrayList;

public class Market extends Fragment {
    private ArrayList<marketClass> marketClasses;

    public Market() {
        marketClasses = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.marketfragment, container, false);
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.marketrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MarketAdapter adapter = new MarketAdapter(marketClasses, getActivity());

        for (int i = 0; i < 6; i++) {
            marketClass marketclass = new marketClass("Kitara Farm", "Ntungamo,Plot23", "+256 708978909", R.drawable.ic_launcher);
            marketClasses.add(marketclass);
            adapter.notifyDataSetChanged();
        }

        recyclerView.setAdapter(adapter);

        return view;
    }

    /*Enable options menu in this fragment*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.menus, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //hide item (sort)
        menu.findItem(R.id.newproduct).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
