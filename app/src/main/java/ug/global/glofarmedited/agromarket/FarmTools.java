package ug.global.glofarmedited.agromarket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.adapterobjects.FarmToolsAdapterObjects;
import ug.global.glofarmedited.agromarket.adapters.FarmToolsRecyclerViewAdapter;

public class FarmTools extends AppCompatActivity {
    private ArrayList<FarmToolsAdapterObjects> farmToolsObjectsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_tools);
        final RecyclerView recyclerView;
        recyclerView = findViewById(R.id.toolsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final FarmToolsRecyclerViewAdapter adapter = new FarmToolsRecyclerViewAdapter(farmToolsObjectsArrayList, this);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/tools");
        Query query = reference.orderByChild("toolname");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                String name = hashMap.get("toolname");
                String price = hashMap.get("toolprice");
                String toolsavailable = hashMap.get("availabletools");
                FarmToolsAdapterObjects farmToolsObjects = new FarmToolsAdapterObjects(price, name, toolsavailable);
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
