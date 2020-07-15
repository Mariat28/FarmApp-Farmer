package ug.global.glofarmedited.agromarket.fragments;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.adapterobjects.SupplierObjects;
import ug.global.glofarmedited.agromarket.adapters.SupplierAdapter;

public class PlantSupplies extends Fragment {

    private ArrayList<SupplierObjects> supplierObjectsArrayList = new ArrayList<>();
    private ArrayList<SupplierObjects> newsupplierObjectsArrayList = new ArrayList<>();
    private SupplierAdapter adapter;
    // private TextView searchresult;
    private androidx.appcompat.widget.SearchView searchView = null;
    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private Query query1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plant__supplies, container, false);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait while we fetch your data...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        recyclerView = view.findViewById(R.id.suppliesrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        final SupplierAdapter adapter = new SupplierAdapter(supplierObjectsArrayList, getContext());
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/supplies");
        query1 = reference.orderByChild("supplytype").equalTo("Plant Supply");
        query1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    progressDialog.dismiss();
                    SupplierObjects supplierObjects = dataSnapshot.getValue(SupplierObjects.class);
                    assert supplierObjects != null;
                    String name = supplierObjects.getSupplyname();
                    String price = supplierObjects.getPrice();
                    String suppliesavailable = supplierObjects.getAvailableamount();
                    SupplierObjects supplyObjects = new SupplierObjects(name, price, suppliesavailable);
                    supplierObjectsArrayList.add(supplyObjects);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

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
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    progressDialog.dismiss();

                }


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
        menu.findItem(R.id.sortmenu).setVisible(false);
        menu.findItem(R.id.settings).setVisible(false);
        menu.findItem(R.id.Profile).setVisible(false);
        menu.findItem(R.id.expenses).setVisible(false);
        MenuItem searchitem = menu.findItem(R.id.search);
        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);
        if (searchitem != null) {
            searchView = (androidx.appcompat.widget.SearchView) searchitem.getActionView();
            assert searchManager != null;
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                        @Override
                        public boolean onQueryTextSubmit(String query) {

                            Toast.makeText(getActivity(), "This is the submitted text" + query, Toast.LENGTH_SHORT).show();

                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            Query query = reference.orderByChild("supplyname").equalTo(newText);
                            // final SupplierAdapter supplierAdapter=new SupplierAdapter(newsupplierObjectsArrayList,getActivity());
                            if (newText.length() == 0) {
                                //getActivity().recreate();

                            } else {
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Iterable<DataSnapshot> snapshotIterable = dataSnapshot.getChildren();
                                        Iterator<DataSnapshot> iterator = snapshotIterable.iterator();
                                        supplierObjectsArrayList.clear();
                                        while (iterator.hasNext()) {
                                            DataSnapshot next = iterator.next();
                                            String match = (String) next.child("supplyname").getValue();
                                            String match1 = (String) next.child("price").getValue();
                                            String match2 = (String) next.child("availableamount").getValue();
                                            SupplierObjects supplyObjects = new SupplierObjects(match, match1, match2);
                                            supplierObjectsArrayList.add(supplyObjects);
                                            adapter.notifyDataSetChanged();
                                            recyclerView.setAdapter(adapter);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                            return true;
                        }
                    });
                }
            });

        } else {
            Toast.makeText(getActivity(), "Item not found", Toast.LENGTH_SHORT).show();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }
}
