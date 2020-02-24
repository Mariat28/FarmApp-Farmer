package com.example.glosales.supporttools.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.glosales.R;

import java.util.ArrayList;
import java.util.Objects;

public class ManualsFragment extends Fragment {
    private ArrayList<String> listViewObjectsArrayList = new ArrayList<>();

  /*  public ManualsFragment(){
        listViewObjectsArrayList=new ArrayList<>();

    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manuals, container, false);
        ListView listView = view.findViewById(R.id.manualslist);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), R.layout.manuals_list_row_item, listViewObjectsArrayList);
        for (int x = 0; x < 20; x++) {
            listViewObjectsArrayList.add("How to eliminate banana weevils - 20th Jan 2020");
            adapter.notifyDataSetChanged();
        }
        listView.setAdapter(adapter);
        return view;
    }

}
