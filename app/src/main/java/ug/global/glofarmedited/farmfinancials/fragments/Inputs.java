package ug.global.glofarmedited.farmfinancials.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;
import ug.global.glofarmedited.farmfinancials.FarmFinancialsMain;
import ug.global.glofarmedited.farmfinancials.adapterobjects.InputObjects;
import ug.global.glofarmedited.farmfinancials.adapters.InputsAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Inputs extends Fragment {
    private ArrayList<InputObjects> inputObjectsArrayList;
    private TextInputEditText name, cost;
    //  private ProgressBar progressBar;
    private TextView noinputstext;
    private ImageView noinputsimage;

    public Inputs() {
        inputObjectsArrayList = new ArrayList<>();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inputs, container, false);
        final RecyclerView recyclerView;
        noinputsimage = view.findViewById(R.id.noinputsimage);
        noinputstext = view.findViewById(R.id.noinputstext);
        recyclerView = view.findViewById(R.id.inputs_recycler);
        //  progressBar = view.findViewById(R.id.progressBar);
        //   progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final InputsAdapter adapter = new InputsAdapter(inputObjectsArrayList, getActivity());
        final String retrieved_farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        assert retrieved_farm_name != null;
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/farms").child(retrieved_farm_name).child("inputs");
        Query query = databaseReference.orderByChild("farmkey").equalTo(retrieved_farm_name);
        final String date = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault()).format(new Date());
        databaseReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists() && (Objects.equals(dataSnapshot.child("date").getValue(String.class), date))) {
                    //progressBar.setVisibility(View.INVISIBLE);
                    noinputsimage.setVisibility(View.INVISIBLE);
                    noinputstext.setVisibility(View.INVISIBLE);
                    InputObjects inputObjects = dataSnapshot.getValue(InputObjects.class);
                    assert inputObjects != null;
                    String name = inputObjects.getInputname();
                    String cost = inputObjects.getInputcost();
                    InputObjects inputObjects1 = new InputObjects(name, cost);
                    inputObjectsArrayList.add(inputObjects1);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);

                } else {
                    //  progressBar.setVisibility(View.INVISIBLE);
                    noinputsimage.setVisibility(View.VISIBLE);
                    noinputstext.setVisibility(View.VISIBLE);
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    // progressBar.setVisibility(View.INVISIBLE);
                    noinputsimage.setVisibility(View.VISIBLE);
                    noinputstext.setVisibility(View.VISIBLE);

                }


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
                androidx.appcompat.app.AlertDialog builder = new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext())).create();
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
                        if (inputname.length() == 0) {
                            name.setError("Please enter expense name to proceed");
                            name.requestFocus();
                        } else if (inputcost.length() == 0) {
                            cost.setError("Please enter expense amount to proceed");
                            cost.requestFocus();
                        } else {

                            String date = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault()).format(new Date());
                            String key = databaseReference.push().getKey();
                            FirebaseInputObjects firebaseInputObjects = new FirebaseInputObjects(inputname, inputcost, retrieved_farm_name, date);
                            assert key != null;
                            databaseReference.child(key).setValue(firebaseInputObjects);
                            databaseReference.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    //  progressBar.setVisibility(View.INVISIBLE);
                                    noinputsimage.setVisibility(View.INVISIBLE);
                                    noinputstext.setVisibility(View.INVISIBLE);
                                    Toast.makeText(getActivity(), "Input added successfully", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                                    startActivity(new Intent(getActivity(), FarmFinancialsMain.class));
                                    getActivity().finish();

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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.menus, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //hide item (sort)
        menu.findItem(R.id.sortmenu).setVisible(false);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.expenses).setVisible(false);
        menu.findItem(R.id.Profile).setVisible(false);


        super.onCreateOptionsMenu(menu, inflater);
    }

    /*Enable options menu in this fragment*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    static class FirebaseInputObjects {
        private String inputname;
        private String inputcost;
        private String farmkey;
        private String date;

        public FirebaseInputObjects() {
        }

        FirebaseInputObjects(String inputname, String inputcost, String farmkey, String date) {
            this.inputname = inputname;
            this.inputcost = inputcost;
            this.farmkey = farmkey;
            this.date = date;
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

        public String getDate() {
            return date;
        }
    }



}

