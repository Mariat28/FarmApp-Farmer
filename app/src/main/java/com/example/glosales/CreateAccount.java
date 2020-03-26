package com.example.glosales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateAccount extends Fragment {

    Context context;
    private TextInputEditText password, name, farmername, confirmpassword, NIN, phone, location;
    private RadioButton crophusbandry, mixed, dairy;
    private RadioGroup farm_category;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
  /*      if(mAuth.getCurrentUser()!=null){

        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        password = view.findViewById(R.id.password);
        name = view.findViewById(R.id.farm_name);
        farmername = view.findViewById(R.id.farmnamee);
        location = view.findViewById(R.id.famlocation);
        confirmpassword = view.findViewById(R.id.passwordconfirm);
        NIN = view.findViewById(R.id.NIN);
        phone = view.findViewById(R.id.phonenumber);
        progressBar = view.findViewById(R.id.progressBar2);

        crophusbandry = view.findViewById(R.id.crops);
        mixed = view.findViewById(R.id.mixed);
        dairy = view.findViewById(R.id.dairy);
        farm_category = view.findViewById(R.id.productcategories);
        Button signIn = view.findViewById(R.id.signinbutton);
        progressBar.setVisibility(View.INVISIBLE);
        mAuth = FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                final String savedpassword = Objects.requireNonNull(password.getText()).toString();
                final String farmlocation = Objects.requireNonNull(location.getText()).toString();
                final String farmname = Objects.requireNonNull(name.getText()).toString();
                final String farmerName = Objects.requireNonNull(farmername.getText()).toString();
                String confirmedpassword = Objects.requireNonNull(confirmpassword.getText()).toString();
                final String nationID = Objects.requireNonNull(NIN.getText()).toString();
                final String farmercontact = Objects.requireNonNull(phone.getText()).toString();

                if (farmerName.length() == 0) {
                    farmername.requestFocus();
                    farmername.setError("Please enter your name");
                } else if (farmname.length() == 0) {
                    name.requestFocus();
                    name.setError("Please enter a farm Name ");
                } else if (nationID.length() == 0) {
                    NIN.requestFocus();
                    NIN.setError("Please enter your National ID number");
                } else if (farmercontact.length() == 0) {
                    phone.requestFocus();
                    phone.setError("Please enter your telephone number");
                } else if (savedpassword.length() == 0) {
                    password.requestFocus();
                    password.setError("Please enter a password");
                } else if (confirmedpassword.length() == 0) {
                    confirmpassword.requestFocus();
                    confirmpassword.setError("Please confirm password");

                } else if (farmlocation.length() == 0) {
                    location.requestFocus();
                    location.setError("Please enter Farm Location");

                } else if (!confirmedpassword.equals(savedpassword)) {
                    confirmpassword.requestFocus();
                    confirmpassword.setError("Passwords do not match!!");
                } else if (!crophusbandry.isChecked() && !dairy.isChecked() && !mixed.isChecked()) {
                    Toast.makeText(getActivity(), "Please select a farm category", Toast.LENGTH_LONG).show();
                } else {
                    int checked_id = farm_category.getCheckedRadioButtonId();
                    final String category = (String) ((RadioButton) view.findViewById(checked_id)).getText();
                    progressBar.setVisibility(View.INVISIBLE);
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmerdetails");
                    DatabaseReference credentials = FirebaseDatabase.getInstance().getReference("/farmercredentials");
                    DatabaseReference farmers = reference.push();
                    DatabaseReference farmercredentials = credentials.push();
                    String key = farmers.getKey();
                    Farmer farmer = new Farmer(farmercontact, nationID, farmname, farmlocation, category, key);
                    assert key != null;
                    reference.child(key).setValue(farmer);
                    Credentials credentials1 = new Credentials(farmerName, savedpassword);
                    farmercredentials.child(key).setValue(credentials1);

                    Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("farmkey", key);
                    startActivity(intent);


                  /*  DatabaseReference farmers = FirebaseDatabase.getInstance().getReference("/farmers");
                    DatabaseReference farms = FirebaseDatabase.getInstance().getReference("/farms");
                    DatabaseReference farmer_child = farmers.push();
                    DatabaseReference farm_child = farms.push();
                    String farmer_key = farmer_child.getKey();
                    String farm_key = farm_child.getKey();

                    Farmer farmer = new Farmer(farmerName, farmercontact, nationID, savedpassword, farm_key);
                    assert farmer_key != null;
                    farmers.child(farmer_key).setValue(farmer);
                    // todo Add regular expressions for the text fields
                    Farm farm = new Farm(farmname, farmlocation, category);
                    assert farm_key != null;
                    farms.child(farm_key).setValue(farm);
                    name.setText("");
                    password.setText("");
                    farmername.setText("");
                    confirmpassword.setText("");
                    NIN.setText("");
                    phone.setText("");
                    location.setText("");

                    farmername.setEnabled(true);
                    name.setEnabled(true);
                    phone.setEnabled(true);
                    NIN.setEnabled(true);
                    password.setEnabled(true);
                    confirmpassword.setEnabled(true);
                    mixed.setChecked(false);
                    dairy.setChecked(false);
                    crophusbandry.setChecked(false);
                    Toast.makeText(getActivity(), "Successfully created your farm account", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.putExtra("farmname", farmname);
                    startActivity(intent);*/
                }
            }
        });

        return view;
    }

    private void registerfarmer() {

    }

    static class Farmer {
        String name;
        String phone;
        String NIN;
        String farmname;
        String farmlocation;
        String category;
        String password;
        String farmkey;

        Farmer(String phone, String NIN, String farmname, String farmlocation, String category, String farmkey) {
            this.name = name;
            this.phone = phone;
            this.NIN = NIN;
            this.farmlocation = farmlocation;
            this.category = category;
            this.farmkey = farmkey;
        }

        public String getFarmkey() {
            return farmkey;
        }

        public String getPhone() {
            return phone;
        }

        public String getNIN() {
            return NIN;
        }

        public String getFarmname() {
            return farmname;
        }

        public String getFarmlocation() {
            return farmlocation;
        }

        public String getCategory() {
            return category;
        }


    }

    static class Credentials {
        String username;
        String password;

        Credentials(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }


    }
}


