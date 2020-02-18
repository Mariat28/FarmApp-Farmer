package com.example.glosales.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.glosales.HomeTabbedActivity;
import com.example.glosales.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Login extends Fragment {
    TextInputEditText password, name;
    Button Login;
    Context context;
    Button logIn;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        password = view.findViewById(R.id.password);
        name = view.findViewById(R.id.farm_name);
        context = getContext();
        logIn = view.findViewById(R.id.loginbutton);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmercredentials");
                final String checkedpassword = password.getText().toString();
                final String checkedname = name.getText().toString();
                Query query = reference.orderByChild("Farm name").equalTo(checkedname);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        String retrievedname = dataSnapshot.child("Farm name").getValue(String.class);
                        String retrievedpassword = dataSnapshot.child("Password").getValue(String.class);

                        progressBar.setVisibility(View.INVISIBLE);


                        if ((checkedpassword.equals(retrievedpassword))) {
                            Intent intent = new Intent(getActivity(), HomeTabbedActivity.class);
                            intent.putExtra("farmname", retrievedname);
                            startActivity(intent);

                        } else {
                            name.setText("");
                            name.setError("Please enter the correct farmname");
                            password.setText("");
                            password.setError("Please enter a valid password");
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

            }
        });

        return view;
    }

}
