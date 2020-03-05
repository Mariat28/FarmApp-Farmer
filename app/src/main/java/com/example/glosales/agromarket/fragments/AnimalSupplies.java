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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalSupplies extends Fragment {
    private ArrayList<SupplierObjects> supplierObjectsArrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant_supplies, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.suppliesrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/supplies");
        Query query = reference.orderByChild("supplytype").equalTo("Animal Supply");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SupplierAdapter adapter = new SupplierAdapter(supplierObjectsArrayList, getContext());
                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                String name = hashMap.get("supplyname");
                String price = hashMap.get("price");
                String suppliesavailable = hashMap.get("availableamount");
                SupplierObjects supplyObjects = new SupplierObjects(name, price, suppliesavailable, R.drawable.ic_account_circle_black_24dp);
                supplierObjectsArrayList.add(supplyObjects);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
