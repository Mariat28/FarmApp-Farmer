package ug.global.glofarmedited.farmfinancials.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.farmfinancials.adapterobjects.SalesObjects;
import ug.global.glofarmedited.farmfinancials.adapters.SalesAdapter;

public class Sales extends Fragment {
    //TODO sales should be reflected from all processed orders
    private ArrayList<SalesObjects> salesObjectsArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        FloatingActionButton newpdt = view.findViewById(R.id.addmyproduct);
        RecyclerView recyclerView = view.findViewById(R.id.productlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SalesAdapter adapter = new SalesAdapter(getActivity(), salesObjectsArrayList);
        for (int i = 0; i < 5; i++) {
            SalesObjects salesObjects = new SalesObjects("Irish Potatoes", "700000");
            SalesObjects salesObjects1 = new SalesObjects("Passsion Fruits", "257000");
            SalesObjects salesObjects2 = new SalesObjects("Matooke", "1000000");
            salesObjectsArrayList.add(salesObjects1);
            salesObjectsArrayList.add(salesObjects2);
            salesObjectsArrayList.add(salesObjects);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);


        }
        newpdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                builder.setView(R.layout.sales_alert);
                builder.setTitle("Add Product");
                builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create();
                builder.show();
            }
        });
        return view;
    }
}
