package ug.global.glofarmedited.farmfinancials.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import ug.global.glofarmedited.farmfinancials.adapterobjects.ExpenseClass;
import ug.global.glofarmedited.farmfinancials.adapters.ExpenseAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Expenses extends Fragment {
    private ArrayList<ExpenseClass> expenseClassArrayList;
    private TextInputEditText expensename, expenseamount;
    private String farm_name, name, amount;
    private Context context;
    private ProgressBar progressBar;
    private ImageView noexpenses;
    private TextView noexpensestext;
    public Expenses() {
        expenseClassArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        final RecyclerView recyclerView;
        noexpenses = view.findViewById(R.id.noexpensesimage);
        noexpensestext = view.findViewById(R.id.noexpensestext);
        recyclerView = view.findViewById(R.id.expense_recycler);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final ExpenseAdapter adapter = new ExpenseAdapter(getActivity(), expenseClassArrayList);
        final String retrieved_farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/expenses");
        Query query = databaseReference.orderByChild("farmkey").equalTo(retrieved_farm_name);
        final String date = new SimpleDateFormat("dd / MM / yyy", Locale.getDefault()).format(new Date());

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists() && (Objects.equals(dataSnapshot.child("date").getValue(String.class), date))) {
                    progressBar.setVisibility(View.INVISIBLE);
                    ExpenseClass expenseClass = dataSnapshot.getValue(ExpenseClass.class);
                    assert expenseClass != null;
                    String name = expenseClass.getExpensename();
                    String amount = expenseClass.getExpenseamount();
                    ExpenseClass expenseClass1 = new ExpenseClass(name, amount);
                    expenseClassArrayList.add(expenseClass1);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), "Expense list cleared..Refresh to see changes", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), FarmFinancialsMain.class));

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    noexpenses.setVisibility(View.VISIBLE);
                    noexpensestext.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton expensefab = view.findViewById(R.id.expensefab);
        expensefab.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InflateParams")
            @Override
            public void onClick(final View v) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/expenses");
                androidx.appcompat.app.AlertDialog builder = new MaterialAlertDialogBuilder(getContext()).create();
                builder.setTitle("Add Expense Details");
                final View view = getLayoutInflater().inflate(R.layout.expense_alert, null);
                builder.setView(view);
                builder.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        expensename = view.findViewById(R.id.expense_name);
                        expenseamount = view.findViewById(R.id.expense_amount);
                        name = expensename.getEditableText().toString();
                        amount = expenseamount.getEditableText().toString();
                        farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                        String key = databaseReference.push().getKey();
                        FirebaseExpenseObjects firebaseExpenseObjects = new FirebaseExpenseObjects(name, amount, farm_name, date);
                        assert key != null;
                        databaseReference.child(key).setValue(firebaseExpenseObjects);
                        databaseReference.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                Toast.makeText(getActivity(), "Expense added successfully", Toast.LENGTH_SHORT).show();
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
        menu.findItem(R.id.Profile).setVisible(false);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.expenses).setVisible(true);

        super.onCreateOptionsMenu(menu, inflater);
    }


    public static class FirebaseExpenseObjects {
        private String expensename;
        private String expenseamount;
        private String farmkey;
        private String date;

        public FirebaseExpenseObjects() {
        }

        FirebaseExpenseObjects(String expensename, String expenseamount, String farmkey, String date) {
            this.expensename = expensename;
            this.expenseamount = expenseamount;
            this.farmkey = farmkey;
            this.date = date;
        }

        public String getExpensename() {
            return expensename;
        }

        public String getExpenseamount() {
            return expenseamount;
        }

        public String getFarmkey() {
            return farmkey;
        }

        public String getDate() {
            return date;
        }
    }

}
