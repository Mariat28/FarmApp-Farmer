package ug.global.glofarmedited.farmfinancials.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;
import ug.global.glofarmedited.farmfinancials.adapterobjects.SalesObjects;
import ug.global.glofarmedited.farmfinancials.adapters.SalesAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Sales extends Fragment {
    private ArrayList<SalesObjects> salesObjectsArrayList = new ArrayList<>();
    private ProgressBar progressBar;
    private StorageReference mStorageRef;
    private MaterialTextView totalsales, expenditure, profits, losses, input;
    private long sum = 0;
    private long expenses = 0;
    private long inputs = 0;
    private long overallsales;
    private long overallinputs;
    private long overallexpenses;
    //ProgressDialog progressDialog;
    private ImageView nosalesimage;
    private TextView nosalestextview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_sales, container, false);
        nosalesimage = view.findViewById(R.id.nosalesimage);
        nosalestextview = view.findViewById(R.id.nosalestext);
        final RecyclerView recyclerView = view.findViewById(R.id.productlist);
        progressBar = view.findViewById(R.id.progressBar);
        FloatingActionButton reportfab = view.findViewById(R.id.reportfloatingActionButton);
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Please wait while we fetch your data");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.create();
        progressDialog.show();
        //progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final SalesAdapter adapter = new SalesAdapter(getActivity(), salesObjectsArrayList);
        final String farm_name = Objects.requireNonNull(getActivity()).getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);

        assert farm_name != null;
        final DatabaseReference expenseref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("expenses");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
        final String timestamp = dateFormat.format(Calendar.getInstance().getTime());
        expenseref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (Objects.equals(dataSnapshot.child("farmkey").getValue(String.class), farm_name) && (dataSnapshot.child("date").getValue(String.class)).equals(timestamp)) {
                    expenses += Long.parseLong(dataSnapshot.child("expenseamount").getValue(String.class));
                    totalexpenses();
                    overallexpenses = expenses++;
                    Log.i("TAG", "overallexpenses: " + overallexpenses);
                    final DatabaseReference oref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("overallexpenses");
                    oref.setValue(overallexpenses);

                    // totalexpenses();
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
        final DatabaseReference inputref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("inputs");
        inputref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (Objects.equals(dataSnapshot.child("farmkey").getValue(String.class), farm_name) && dataSnapshot.exists() && (dataSnapshot.child("date").getValue(String.class)).equals(timestamp)) {
                    inputs += Long.parseLong(dataSnapshot.child("inputcost").getValue(String.class));
                    totalinputs();
                    overallinputs = inputs++;
                    final DatabaseReference oref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("overallinputs");
                    oref.setValue(overallinputs);


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
        final DatabaseReference salesref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("sales");

        salesref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                String salestimestamp = dataSnapshot.child("timestamp").getValue(String.class);
                assert salestimestamp != null;
                if (dataSnapshot.exists() && salestimestamp.equals(timestamp)) {
                    nosalesimage.setVisibility(View.INVISIBLE);
                    nosalestextview.setVisibility(View.INVISIBLE);
                    progressDialog.dismiss();
                    SalesObjects salesObjects = dataSnapshot.getValue(SalesObjects.class);
                    assert salesObjects != null;
                    String productname = salesObjects.getProductname();
                    Long salesamount = salesObjects.getProductcost();
                    String time = salesObjects.getTimestamp();
                    SalesObjects salesObjects1 = new SalesObjects(productname, salesamount, time);
                    salesObjectsArrayList.add(salesObjects1);
                    adapter.notifyDataSetChanged();
                    sum += dataSnapshot.child("productcost").getValue(Long.class);
                    totalsales();
                    //   totalexpenses();
                    //  totalinputs();
                    long profitsmade = sum - (expenses + inputs);
                    final DatabaseReference profitref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("totalsales");
                    final String farm_name = Objects.requireNonNull(getActivity()).getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
                    String timestamp = dateFormat.format(Calendar.getInstance().getTime());
                    Date today = new Date();
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(today);
                    int dayofweek = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = (calendar.get(Calendar.MONTH)) + 1;
                    String key = "date:" + dayofweek + month;
                    if (profitsmade > 0) {
                        profitref.child(key).child("profits").setValue(profitsmade);

                    } else {
                        profitref.child(key).child("losses").setValue(profitsmade);
                        //Toast.makeText(getActivity(), "first loss saved", Toast.LENGTH_SHORT).show();

                    }
                    overallsales = sum++;
                    assert farm_name != null;
                    final DatabaseReference oref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("overallsales");
                    oref.setValue(overallsales);


                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    progressDialog.dismiss();
                    progressDialog.setCancelable(true);
                    nosalesimage.setVisibility(View.VISIBLE);
                    nosalestextview.setVisibility(View.VISIBLE);

                }
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
        salesref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    progressDialog.dismiss();
                    progressDialog.setCancelable(true);
                    nosalesimage.setVisibility(View.VISIBLE);
                    nosalestextview.setVisibility(View.VISIBLE);
                    //    Toast.makeText(getActivity(), "No Sales For  "+timestamp, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //create a date string

        reportfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext())).create();
                builder.setTitle("Daily Sales Report");
                builder.setIcon(R.drawable.ic_financial);
                @SuppressLint("InflateParams") View view1 = getLayoutInflater().inflate(R.layout.sales_alert, null);
                String date = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault()).format(new Date());
                TextView datetext = view1.findViewById(R.id.salesdate);
                datetext.setText(date);
                totalsales = view1.findViewById(R.id.totaldailysales);
                Log.i("TAG", "fabsum " + sum);
                totalsales.setText(String.valueOf(sum));
                input = view1.findViewById(R.id.dailyinputs);
                input.setText(String.valueOf(inputs));
                expenditure = view1.findViewById(R.id.dailyexpense);
                expenditure.setText(String.valueOf(expenses));
                losses = view1.findViewById(R.id.dailyloss);
                profits = view1.findViewById(R.id.dailyprofits);
                long profitsmade = sum - (expenses + inputs);
                DatabaseReference profitref = FirebaseDatabase.getInstance().getReference("/totalsales");
                final String farm_name = Objects.requireNonNull(getActivity()).getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
                String timestamp = dateFormat.format(Calendar.getInstance().getTime());
                Date today = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                int dayofweek = calendar.get(Calendar.DAY_OF_MONTH);
                int month = (calendar.get(Calendar.MONTH)) + 1;

                String key = farm_name + dayofweek + month;

                // Toast.makeText(getActivity(), "Profits saved", Toast.LENGTH_SHORT).show();
                if (profitsmade > 0) {
                    profits.setText(String.valueOf(profitsmade));
                    losses.setText("0");
                    // profitref.child(key).child("profits").setValue(profitsmade);
                    // Toast.makeText(getActivity(), "profits saved", Toast.LENGTH_SHORT).show();


                }
                if (profitsmade < 0) {
                    losses.setText(String.valueOf(profitsmade).replace("-", ""));
                    profits.setText("0");
                    // profitref.child(key).child("losses").setValue(Long.parseLong(losses.getText().toString()));
                    //  Toast.makeText(getActivity(), "losses saved", Toast.LENGTH_SHORT).show();
                }


                builder.setView(view1);

                builder.setButton(DialogInterface.BUTTON_POSITIVE, "BACK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });


        return view;
    }


    private void totalinputs() {
        final String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        assert farm_name != null;
        DatabaseReference totalinputref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("totalinputs");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());

        String timestamp = dateFormat.format(Calendar.getInstance().getTime());
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int dayofweek = calendar.get(Calendar.DAY_OF_MONTH);
        int month = (calendar.get(Calendar.MONTH)) + 1;

        String key = "date:" + dayofweek + month;
        //String key=totalinputref.push().getKey();
        TotalObject totalObject = new TotalObject(inputs, timestamp);

        totalinputref.child(key).setValue(totalObject);

    }

    private void totalexpenses() {

        final String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        assert farm_name != null;
        DatabaseReference totalexpenseref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("totalexpenses");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());
        String timestamp = dateFormat.format(Calendar.getInstance().getTime());
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int dayofweek = calendar.get(Calendar.DAY_OF_MONTH);
        int month = (calendar.get(Calendar.MONTH)) + 1;

        String key = "date:" + dayofweek + month;
        // String key=totalexpenseref.push().getKey();
        Log.i("TAG", "totalexpenses: " + expenses);
        TotalObject totalObject = new TotalObject(expenses, timestamp);

        totalexpenseref.child(key).setValue(totalObject);


    }

    private void totalsales() {

        final String farm_name = Objects.requireNonNull(getActivity()).getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        assert farm_name != null;
        DatabaseReference totalsalesref = FirebaseDatabase.getInstance().getReference("/farms").child(farm_name).child("totalsales");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.getDefault());

        String timestamp = dateFormat.format(Calendar.getInstance().getTime());
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        int dayofweek = calendar.get(Calendar.DAY_OF_MONTH);
        int month = (calendar.get(Calendar.MONTH)) + 1;

        String key = "date:" + dayofweek + month;
        //String key=totalsalesref.push().getKey();
        TotalObject totalObject = new TotalObject(sum, timestamp);

        totalsalesref.child(key).setValue(totalObject);

    }
}

class TotalObject {
    private String key;
    private String farmname;
    private long amount;
    private String date;


    public TotalObject(long amount, String date) {
        this.key = key;
        this.farmname = farmname;
        this.amount = amount;
        this.date = date;
    }

    public TotalObject() {
    }

    public String getKey() {
        return key;
    }

    public String getFarmname() {
        return farmname;
    }

    public long getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
}

