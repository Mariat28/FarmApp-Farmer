package com.example.glosales.agromarket.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.MarketObject;
import com.example.glosales.agromarket.adapters.MarketAdapter;

import java.util.ArrayList;

public class Market extends Fragment {
    private ArrayList<MarketObject> MarketObjects;

    public Market() {
        MarketObjects = new ArrayList<>();
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
        MarketAdapter adapter = new MarketAdapter(MarketObjects, getActivity());

        for (int i = 0; i < 6; i++) {
            MarketObject marketclass = new MarketObject("Kitara Farm", "Ntungamo,Plot23", "+256 708978909", R.drawable.ic_launcher);
            MarketObjects.add(marketclass);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.menus, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //hide item (sort)
        menu.findItem(R.id.newproduct).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
