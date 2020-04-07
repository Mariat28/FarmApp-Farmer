package ug.global.glofarmedited.farmfinancials.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;
import ug.global.glofarmedited.farmfinancials.adapterobjects.InputObjects;
import ug.global.glofarmedited.farmfinancials.adapters.InputsAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Inputs extends Fragment {
    private ArrayList<InputObjects> inputObjectsArrayList;
    private TextInputEditText name, cost;

    public Inputs() {
        inputObjectsArrayList = new ArrayList<>();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inputs, container, false);
        final RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.inputs_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final InputsAdapter adapter = new InputsAdapter(inputObjectsArrayList, getActivity());
        final String retrieved_farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/inputs");
        Query query = databaseReference.orderByChild("farmkey").equalTo(retrieved_farm_name);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
                    InputObjects inputObjects = dataSnapshot.getValue(InputObjects.class);
                    assert inputObjects != null;
                    String name = inputObjects.getInputname();
                    String cost = inputObjects.getInputcost();
                    InputObjects inputObjects1 = new InputObjects(name, cost);
                    inputObjectsArrayList.add(inputObjects1);
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


        FloatingActionButton inputsfab = view.findViewById(R.id.inputsfab);
        inputsfab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InflateParams")
            @Override
            public void onClick(View v) {
                final DatabaseReference inputreference = FirebaseDatabase.getInstance().getReference("/inputs");
                androidx.appcompat.app.AlertDialog builder = new MaterialAlertDialogBuilder(getContext()).create();
                final View view = getLayoutInflater().inflate(R.layout.inputs_alert, null);
                builder.setView(view);
                builder.setTitle("Add Input Details");
                builder.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = view.findViewById(R.id.inputname);
                        cost = view.findViewById(R.id.inputamount);
                        String inputname = name.getEditableText().toString();
                        String inputcost = cost.getEditableText().toString();
                        String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                        String key = inputreference.push().getKey();
                        FirebaseInputObjects firebaseInputObjects = new FirebaseInputObjects(inputname, inputcost, farm_name);
                        assert key != null;
                        inputreference.child(key).setValue(firebaseInputObjects);
                        inputreference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                Toast.makeText(getActivity(), "Input added successfully", Toast.LENGTH_SHORT).show();
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
                });
                builder.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

                builder.show();
            }

        });

        return view;
    }

    static class FirebaseInputObjects {
        private String inputname;
        private String inputcost;
        private String farmkey;

        public FirebaseInputObjects() {
        }

        public FirebaseInputObjects(String inputname, String inputcost, String farmkey) {
            this.inputname = inputname;
            this.inputcost = inputcost;
            this.farmkey = farmkey;
        }

        public String getInputname() {
            return inputname;
        }

        public String getInputcost() {
            return inputcost;
        }

        public String getFarmkey() {
            return farmkey;
        }
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


        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.expenses) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Are You sure you want to delete all expenses");
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();

        }


        return super.onOptionsItemSelected(item);
    }


}

