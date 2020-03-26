package com.example.glosales.farmfinancials;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.glosales.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class IncomeFragment extends Fragment {
    private ArrayList<String> listViewObjectsArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.new_income_layout, container, false);
        FloatingActionButton newpdt = view.findViewById(R.id.addmyproduct);
        newpdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
                builder.setView(R.layout.incomealert);
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
        ListView listView = view.findViewById(R.id.productlist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>((Objects.requireNonNull(getActivity())), R.layout.manuals_list_row_item, listViewObjectsArrayList);
        listViewObjectsArrayList.add("Beans");
        listViewObjectsArrayList.add("Milk");
        listViewObjectsArrayList.add("Gnuts");
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);


/*        Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        String retrievedfarmname = intent.getStringExtra("farmname");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmercredentials");
        farmname = view.findViewById(R.id.farm_name);
        Query query = reference.orderByChild("Farm name").equalTo(retrievedfarmname);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String newname = dataSnapshot.child("Farm name").getValue(String.class);
                farmname.setText(newname);
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
        total_stock = view.findViewById(R.id.total_stock_view);
        TextView sales = view.findViewById(R.id.sales_view);
        openingstock = view.findViewById(R.id.openingstock);

        ImageView sendbutton = view.findViewById(R.id.sendbutton);
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/Stock_details");
                final String farmnamee = farmname.getText().toString();
                String openingstocks = Objects.requireNonNull(openingstock.getText()).toString();
                DatabaseReference child = reference.push();
                String key = child.getKey();
                assert key != null;
                reference.child(key).child("opening_stock").setValue(openingstocks);
                reference.child(key).child("farm_name").setValue(farmnamee);
                openingstock.setText("");
                Toast.makeText(getActivity(), "Opening Stock Added", Toast.LENGTH_SHORT).show();
                Query query = reference.orderByChild("farm_name");
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String retrievedfarmname = dataSnapshot.child("farm_name").getValue(String.class);
                        if (farmnamee.equals(retrievedfarmname)) {
                            String retrieved_total = dataSnapshot.child("opening_stock").getValue(String.class);
                            total_stock.setText(retrieved_total);
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

            }
        });*/

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
        menu.findItem(R.id.expenses).setVisible(false);
        menu.findItem(R.id.search).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.settings) {
            Toast.makeText(getActivity(), "Settings clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Profile) {
            Toast.makeText(getActivity(), "Profile clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.helpline) {
            Toast.makeText(getActivity(), "Feature coming soon", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);
    }


}
