package ug.global.glofarmedited.supporttools.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.supporttools.adapters.ListViewObjects;
import ug.global.glofarmedited.supporttools.adapters.ManualsRecyclerViewAdapter;

public class Manuals extends Fragment {
    private ArrayList<ListViewObjects> listViewObjectsArrayList;

    public Manuals() {
        listViewObjectsArrayList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manuals, container, false);
        final RecyclerView recyclerView = view.findViewById(R.id.faqrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final ManualsRecyclerViewAdapter manualsRecyclerViewAdapter = new ManualsRecyclerViewAdapter(listViewObjectsArrayList, getActivity());
        DatabaseReference faqreference = FirebaseDatabase.getInstance().getReference("/Frequently Asked Questions");
        faqreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String question = dataSnapshot.child("question").getValue(String.class);
                String answer = dataSnapshot.child("answer").getValue(String.class);
                ListViewObjects listViewObjects = new ListViewObjects(answer, question);
                listViewObjectsArrayList.add(listViewObjects);
                manualsRecyclerViewAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(manualsRecyclerViewAdapter);


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


        return view;
    }

}
