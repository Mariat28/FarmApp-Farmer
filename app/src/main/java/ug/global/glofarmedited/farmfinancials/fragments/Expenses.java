package ug.global.glofarmedited.farmfinancials.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
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
import ug.global.glofarmedited.farmfinancials.adapterobjects.ExpenseClass;
import ug.global.glofarmedited.farmfinancials.adapters.ExpenseAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Expenses extends Fragment {
    private ArrayList<ExpenseClass> expenseClassArrayList;
    private TextInputEditText expensename, expenseamount;
    private String farm_name, name, amount;
    private Context context;

    public Expenses() {
        expenseClassArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        final RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.expense_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final ExpenseAdapter adapter = new ExpenseAdapter(getActivity(), expenseClassArrayList);
        final String retrieved_farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/expenses");
        Query query = databaseReference.orderByChild("farmkey").equalTo(retrieved_farm_name);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if (dataSnapshot.exists()) {
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

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

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
                        FirebaseExpenseObjects firebaseExpenseObjects = new FirebaseExpenseObjects(name, amount, farm_name);
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

    public static class FirebaseExpenseObjects {
        private String expensename;
        private String expenseamount;
        private String farmkey;

        public FirebaseExpenseObjects() {
        }

        FirebaseExpenseObjects(String expensename, String expenseamount, String farmkey) {
            this.expensename = expensename;
            this.expenseamount = expenseamount;
            this.farmkey = farmkey;
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
    }

}
