package ug.global.glofarmedited.supporttools.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.supporttools.adapters.NoticesAdapter;
import ug.global.glofarmedited.supporttools.adapters.NoticesObjects;


public class Notices extends Fragment {
    private ArrayList<NoticesObjects> noticesObjectsArrayList = new ArrayList<>();
/*
    public Notices() {
        // Required empty public constructor
        noticesObjectsArrayList = new ArrayList<>();
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notices, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.noticesrecycler);
        TextView notice = view.findViewById(R.id.notice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        NoticesAdapter adapter = new NoticesAdapter(noticesObjectsArrayList, getActivity());
       /* for (int i = 0; i < 10; i++) {
            NoticesObjects noticesObjects = new NoticesObjects("CORONA VIRUS OUTBREAK IN UGANDA",
                    getResources().getString(R.string.description), "20th feb 2020");
            noticesObjectsArrayList.add(noticesObjects);
            adapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(adapter);*/
        notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse("globalautosystems.co.ug");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
