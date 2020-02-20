package com.example.glosales.SupportTools;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.glosales.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Supporttools extends AppCompatActivity {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supporttools);

        // get the listview
        expListView = findViewById(R.id.expandablelist);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Manuals");
        listDataHeader.add("Notices");
        listDataHeader.add("Success Stories.");

        // Adding child data
        List<String> manuals = new ArrayList<String>();
        manuals.add("How to get rid Of Banana wilt");
        manuals.add("Ten Steps to defeat bean weavils");
        manuals.add("Cattle Dipping process");

        List<String> notices = new ArrayList<String>();
        notices.add("Corona Virus In Uganda");
        notices.add("Foot and Mouth OutBreak");


        List<String> SuccessStories = new ArrayList<String>();
        SuccessStories.add("Battling Banana wilt....Mr Nahabwe");

        listDataChild.put(listDataHeader.get(0), manuals); // Header, Child data
        listDataChild.put(listDataHeader.get(1), notices);
        listDataChild.put(listDataHeader.get(2), SuccessStories);
    }
}
