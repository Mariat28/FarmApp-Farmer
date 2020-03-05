package com.example.glosales.agromarket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.FarmToolsObjects;
import com.example.glosales.agromarket.adapters.FarmToolsAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FarmTools extends AppCompatActivity {
    private ArrayList<FarmToolsObjects> farmToolsObjectsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_tools);
        Toolbar toolbar = findViewById(R.id.farmtoolbar);
        toolbar.setTitle("Tools");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        final RecyclerView recyclerView;
        recyclerView = findViewById(R.id.toolsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final FarmToolsAdapter adapter = new FarmToolsAdapter(farmToolsObjectsArrayList, this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/tools");
        Query query = reference.orderByChild("toolname");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                String name = hashMap.get("toolname");
                String price = hashMap.get("toolprice");
                String toolsavailable = hashMap.get("availabletools");
                FarmToolsObjects farmToolsObjects = new FarmToolsObjects(price, name, toolsavailable);
                farmToolsObjectsArrayList.add(farmToolsObjects);
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
    }
}
