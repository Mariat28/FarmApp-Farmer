package com.example.glosales.supporttools.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.glosales.R;
import com.example.glosales.supporttools.ListViewObjects;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticesFragment extends Fragment {
    private ArrayList<ListViewObjects> listViewObjectsArrayList;


    public NoticesFragment() {
        // Required empty public constructor
        listViewObjectsArrayList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_notices, container, false);
    }

}
