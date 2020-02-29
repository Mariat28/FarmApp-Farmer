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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.SupplierObjects;
import com.example.glosales.agromarket.adapters.SupplierAdapter;

import java.util.ArrayList;

public class AnimalSupplies extends Fragment {
    private ArrayList<SupplierObjects> supplierObjectsArrayList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant_supplies, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.suppliesrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        SupplierAdapter adapter = new SupplierAdapter(supplierObjectsArrayList, getContext());
        for (int i = 0; i < 10; i++) {
            SupplierObjects supplierObjects = new SupplierObjects("Norotraz", R.drawable.ic_account_circle_black_24dp);
            supplierObjectsArrayList.add(supplierObjects);
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
        menu.findItem(R.id.settings).setVisible(false);
        menu.findItem(R.id.Profile).setVisible(false);
        menu.findItem(R.id.expenses).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
