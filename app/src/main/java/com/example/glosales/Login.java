package com.example.glosales;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class Login extends Fragment {
    private TextInputEditText password;
    private TextInputEditText name;
    Button Login;
    private Context context;
    private Button logIn;
    private ProgressBar progressBar;

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
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                /*intent.putExtra("farmname", retrievedname);*/
//                startActivity(intent);

                progressBar.setVisibility(View.VISIBLE);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmers");
                final String checkedpassword = Objects.requireNonNull(password.getText()).toString();
                final String checkedname = Objects.requireNonNull(name.getText()).toString();
                Query query = reference.orderByChild("name").equalTo(checkedname);
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.i(TAG, "onChildAdded: " + dataSnapshot.getValue());
                        String retrievedname = dataSnapshot.child("Farm name").getValue(String.class);
                        String retrievedpassword = dataSnapshot.child("Password").getValue(String.class);

                        progressBar.setVisibility(View.INVISIBLE);


                        if ((checkedpassword.equals(retrievedpassword))) {
                            Intent intent = new Intent(getActivity(), MainActivity.class);
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
