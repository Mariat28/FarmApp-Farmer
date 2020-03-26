package ug.global.glofarmedited.agromarket.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.adapterobjects.OrderObject;
import ug.global.glofarmedited.agromarket.adapters.OrdersAdapter;

public class Orders extends Fragment {
    private ArrayList<OrderObject> orderObjects = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.ordersrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        OrdersAdapter adapter = new OrdersAdapter(orderObjects, getActivity());

        for (int i = 0; i < 6; i++) {
            OrderObject orders = new OrderObject("Nahabwe Edwin", "0705976941", "3 bags of Irish Potatoes");
            orderObjects.add(orders);
            adapter.notifyDataSetChanged();
        }

        recyclerView.setAdapter(adapter);

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
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();
/*

        if (id == R.id.search) {

            Intent intent = new Intent(getActivity(), AddProduct.class);
            startActivity(intent);

        }
*/


        return super.onOptionsItemSelected(item);
    }


}
