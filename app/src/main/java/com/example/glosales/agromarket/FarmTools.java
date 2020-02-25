package com.example.glosales.agromarket;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.FarmToolsObjects;
import com.example.glosales.agromarket.adapters.FarmToolsAdapter;

import java.util.ArrayList;

public class FarmTools extends AppCompatActivity {
    private ArrayList<FarmToolsObjects> farmToolsObjectsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_tools);
        RecyclerView recyclerView;
        recyclerView = findViewById(R.id.toolsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FarmToolsAdapter adapter = new FarmToolsAdapter(farmToolsObjectsArrayList, this);

        for (int i = 0; i < 15; i++) {
            FarmToolsObjects farmToolsObjects = new FarmToolsObjects("Nahabwe Edwin", "0705976941", "Located in Kabale Town, Plot 56", R.drawable.ic_phone_black_24dp, R.drawable.ic_account_circle_black_24dp);
            farmToolsObjectsArrayList.add(farmToolsObjects);
            adapter.notifyDataSetChanged();
        }

        recyclerView.setAdapter(adapter);
    }
}
