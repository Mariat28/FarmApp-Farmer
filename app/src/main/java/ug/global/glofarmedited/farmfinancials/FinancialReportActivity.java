package ug.global.glofarmedited.farmfinancials;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Calendar;
import java.util.Objects;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;

public class FinancialReportActivity extends AppCompatActivity {
    private static final String TAG = "my date";
    TextView selectdate, selecteddate;
    ImageView calendar;
    private MaterialTextView totalsales, expenditure, profits, losses, dailyinputs;

    private long totaldateinputs, totaldatesales, totaldateexpenses, profit, loss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_report);
        setTitle("Financial Report");
        profits = findViewById(R.id.dailyprofits);
        losses = findViewById(R.id.dailyloss);
        expenditure = findViewById(R.id.dailyexpense);
        totalsales = findViewById(R.id.totaldailysales);
        dailyinputs = findViewById(R.id.dailyinputs);
        selectdate = findViewById(R.id.selectdate);
        calendar = findViewById(R.id.calendarview);
        selecteddate = findViewById(R.id.selecteddate);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //calendar instance
                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                //date picker dialog
                final DatePickerDialog datePickerDialog = new DatePickerDialog(FinancialReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        final String farm_name = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                        DatabaseReference totalsalesref = FirebaseDatabase.getInstance().getReference("/totalsales");
                        if (dayOfMonth < 10) {
                            selecteddate.setText("0" + dayOfMonth + "/" + "0" + (month + 1) + "/" + year);
                            final String selecteddates = selecteddate.getText().toString();
                            final String key = farm_name + dayOfMonth + (month + 1);
                            Log.i(TAG, "savereport: " + key);
                            Query salesquery = totalsalesref.orderByChild("key").equalTo(key);
                            salesquery.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates) && dataSnapshot.child("profits").exists()) {
                                        totaldatesales = dataSnapshot.child("amount").getValue(long.class);
                                        profit = dataSnapshot.child("profits").getValue(long.class);
                                        totalsales.setText(String.valueOf(totaldatesales));
                                        profits.setText(String.valueOf(profit));
                                    } else if ((dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates) && dataSnapshot.child("losses").exists())) {
                                        totaldatesales = dataSnapshot.child("amount").getValue(long.class);
                                        loss = dataSnapshot.child("losses").getValue(long.class);
                                        totalsales.setText(String.valueOf(totaldatesales));
                                        losses.setText(String.valueOf(loss).replace("-", ""));

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
                            DatabaseReference totalexpensesref = FirebaseDatabase.getInstance().getReference("/totalexpenses");
                            Query expensequery = totalexpensesref.orderByChild("key").equalTo(key);
                            expensequery.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates)) {
                                        totaldateexpenses = dataSnapshot.child("amount").getValue(long.class);
                                        expenditure.setText(String.valueOf(totaldateexpenses));
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
                            DatabaseReference totalinputref = FirebaseDatabase.getInstance().getReference("/totalinputs");
                            Query query = totalinputref.orderByChild("key").equalTo(key);
                            Log.i(TAG, "savereport: " + selecteddates);
                            query.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    Log.i(TAG, "onChildAdded: " + dataSnapshot);
                                    if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates)) {
                                        totaldateinputs = dataSnapshot.child("amount").getValue(long.class);
                                        dailyinputs.setText(String.valueOf(totaldateinputs));
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


                        } else {
                            selecteddate.setText(dayOfMonth + "/" + "0" + (month + 1) + "/" + year);
                            final String selecteddates = selecteddate.getText().toString();
                            final String key = farm_name + dayOfMonth + (month + 1);
                            Log.i(TAG, "savereport: " + key);
                            Query salesquery = totalsalesref.orderByChild("key").equalTo(key);
                            salesquery.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates) && dataSnapshot.child("profits").exists()) {
                                        totaldatesales = dataSnapshot.child("amount").getValue(long.class);
                                        profit = dataSnapshot.child("profits").getValue(long.class);
                                        totalsales.setText(String.valueOf(totaldatesales));
                                        profits.setText(String.valueOf(profit));
                                    } else if ((dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates) && dataSnapshot.child("losses").exists())) {
                                        totaldatesales = dataSnapshot.child("amount").getValue(long.class);
                                        loss = dataSnapshot.child("losses").getValue(long.class);
                                        totalsales.setText(String.valueOf(totaldatesales));
                                        losses.setText(String.valueOf(loss).replace("-", ""));

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
                            DatabaseReference totalexpensesref = FirebaseDatabase.getInstance().getReference("/totalexpenses");
                            Query expensequery = totalexpensesref.orderByChild("key").equalTo(key);
                            expensequery.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates)) {
                                        totaldateexpenses = dataSnapshot.child("amount").getValue(long.class);
                                        expenditure.setText(String.valueOf(totaldateexpenses));
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
                            DatabaseReference totalinputref = FirebaseDatabase.getInstance().getReference("/totalinputs");
                            Query query = totalinputref.orderByChild("key").equalTo(key);
                            Log.i(TAG, "savereport: " + selecteddates);
                            query.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    Log.i(TAG, "onChildAdded: " + dataSnapshot);
                                    if (dataSnapshot.exists() && Objects.equals(dataSnapshot.child("date").getValue(String.class), selecteddates)) {
                                        totaldateinputs = dataSnapshot.child("amount").getValue(long.class);
                                        dailyinputs.setText(String.valueOf(totaldateinputs));
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


                    }
                }, year, month, day);
                datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        datePickerDialog.dismiss();
                        //  selecteddate.setText(day + "/" +"0"+ (month + 1) + "/" + year);

                    }
                });
                datePickerDialog.show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        long profitsmade = totaldatesales - (totaldateexpenses + totaldateinputs);
        if (profitsmade > 0) {
            profits.invalidate();
            profits.setText(String.valueOf(profitsmade));
            losses.setText("0");

        } else {
            losses.setText(String.valueOf(profitsmade).replace("-", ""));
            profits.setText("0");
        }

    }

}

