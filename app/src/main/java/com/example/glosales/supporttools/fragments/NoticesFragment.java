package com.example.glosales.supporttools.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.supporttools.adapters.NoticesAdapter;
import com.example.glosales.supporttools.adapters.NoticesObjects;

import java.util.ArrayList;

public class NoticesFragment extends Fragment {
    private ArrayList<NoticesObjects> noticesObjectsArrayList;

    public NoticesFragment() {
        // Required empty public constructor
        noticesObjectsArrayList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notices, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.noticesrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NoticesAdapter adapter = new NoticesAdapter(noticesObjectsArrayList, getActivity());
        for (int i = 0; i < 10; i++) {
            NoticesObjects noticesObjects = new NoticesObjects("CORONA VIRUS OUTBREAK IN UGANDA",
                    getResources().getString(R.string.description), "20th feb 2020");
            noticesObjectsArrayList.add(noticesObjects);
            adapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(adapter);
        return view;
    }

}
