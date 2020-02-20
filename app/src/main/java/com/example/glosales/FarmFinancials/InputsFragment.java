package com.example.glosales.FarmFinancials;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.FarmFinancials.Adapters.ExpenseAdapter;
import com.example.glosales.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class InputsFragment extends Fragment {
    FloatingActionButton newinputfab;
    private ArrayList<ExpenseClass> expenseclass;

    public InputsFragment() {
        expenseclass = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inputs, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.inputsrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        ExpenseAdapter adapter;
        adapter = new ExpenseAdapter(getActivity(), expenseclass);


        return view;
    }

    /*Enable options menu in this fragment*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
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
