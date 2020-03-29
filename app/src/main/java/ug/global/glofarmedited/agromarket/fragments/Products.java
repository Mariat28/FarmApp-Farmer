package ug.global.glofarmedited.agromarket.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.AddNewProduct;
import ug.global.glofarmedited.agromarket.adapterobjects.ProductObjects;
import ug.global.glofarmedited.agromarket.adapters.ProductsAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Products extends Fragment {
    private ArrayList<ProductObjects> productObjectsArrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        FloatingActionButton addproduct = view.findViewById(R.id.addproduct);
        recyclerView = view.findViewById(R.id.productsrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ProductsAdapter productsAdapter = new ProductsAdapter(productObjectsArrayList, getActivity());
        final String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        DatabaseReference product_reference = FirebaseDatabase.getInstance().getReference("/products");
        final String key = product_reference.getKey();
        assert key != null;
        Query query = product_reference.orderByChild("farmkey").equalTo(farm_name);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {

                    HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                    Log.i("checking data", "Checking data" + hashMap.toString());
                    String name = hashMap.get("productname");
                    String description = hashMap.get("productdescription");
                    String price = hashMap.get("productprice");
                   /* assert farm_name != null;
                    assert key != null;
                    String name=dataSnapshot.child("productname").getValue(String.class);
                    String description=dataSnapshot.child("productdescription").getValue(String.class);
                    Log.i("checking data", "Checking data"+name+description);*/
                    ProductObjects productObjects = new ProductObjects(name, description, price);
                    productObjectsArrayList.add(productObjects);

                    productsAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(productsAdapter);

                }

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

        addproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddNewProduct.class));
            }
        });
        return view;
    }


    }


