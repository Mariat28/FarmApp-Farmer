package ug.global.glofarmedited.supporttools;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

interface myCallback {
    void onCallback(String value);

}

public class ExpandableListItems {
    public static HashMap<String, List<String>> getData() {
        final HashMap<String, List<String>> expandablelistDetail = new HashMap<String, List<String>>();
        final List<String> questionone = new ArrayList<String>();

        DatabaseReference faqreference = FirebaseDatabase.getInstance().getReference("/Frequently Asked Questions");
        faqreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String question = dataSnapshot.child("question").getValue(String.class);
                String answer = dataSnapshot.child("answer").getValue(String.class);
                questionone.add(answer);
                expandablelistDetail.put(question, questionone);


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
        });/*
        questionone.add("The Government of Uganda would like to inform the general public that there has been an outbreak of corona in\n" +
                "        the country. 3 cases have been reported in Mbale, 5 cases in Kabale and 20 cases in Isingiro. In this regard, every one is advised to keep minimum\n" +
                "        contact with all collegeaus or any suspected corona Virus victims Read More....");*/
       /* List <String>questiontwo=new ArrayList<String>();
        questiontwo.add("The Government of Uganda would like to inform the general public that there has been an outbreak of corona in\n" +
                "        the country. 3 cases have been reported in Mbale, 5 cases in Kabale and 20 cases in Isingiro. In this regard, every one is advised to keep minimum\n" +
                "        contact with all collegeaus or any suspected corona Virus victims Read More....");

        expandablelistDetail.put("How to prevent Foot and Mouth Disease among your cattle",questiontwo);*/


        return expandablelistDetail;
    }
}

