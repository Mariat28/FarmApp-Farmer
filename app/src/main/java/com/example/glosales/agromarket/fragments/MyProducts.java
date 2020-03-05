package com.example.glosales.agromarket.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.Productdetails;
import com.example.glosales.agromarket.adapterobjects.PlaceObject;
import com.example.glosales.agromarket.adapters.MyProductsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyProducts extends Fragment {
    FloatingActionButton marketfab;
    private ArrayList<PlaceObject> placeObjects;

    public MyProducts() {

        placeObjects = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.myproductsfragment, container, false);
        ImageView addproduct = view.findViewById(R.id.addpicture);
        FloatingActionButton productfab = view.findViewById(R.id.marketfab);
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        MyProductsAdapter adapter = new MyProductsAdapter(getActivity(), placeObjects);
        for (int x = 0; x < 2; x++) {
            PlaceObject productdetails = new PlaceObject("Irish Potatoes", "All the way from kabaale");
            placeObjects.add(productdetails);
            adapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(adapter);
        productfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Productdetails.class);
                startActivity(intent);
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
        menu.findItem(R.id.expenses).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.newproduct) {
            Intent intent = new Intent(getActivity(), Productdetails.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

}
