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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class CreateAccount extends Fragment {

    Context context;
    private TextInputEditText password, name, farmername, confirmpassword, NIN, phone;
    private RadioButton crophusbandry, mixed, dairy;
    private RadioGroup farm_category;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_create_account, container, false);
        password = view.findViewById(R.id.password);
        name = view.findViewById(R.id.farm_name);
        farmername = view.findViewById(R.id.farmnamee);
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


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String savedpassword = Objects.requireNonNull(password.getText()).toString();
                String farmname = Objects.requireNonNull(name.getText()).toString();
                String farmerName = Objects.requireNonNull(farmername.getText()).toString();
                String confirmedpassword = Objects.requireNonNull(confirmpassword.getText()).toString();
                String nationID = Objects.requireNonNull(NIN.getText()).toString();
                String farmercontact = Objects.requireNonNull(phone.getText()).toString();
                String savedcrop = crophusbandry.getText().toString();
                String mixedfarming = mixed.getText().toString();
                String dairyfarming = dairy.getText().toString();

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

                } else if (!confirmedpassword.equals(savedpassword)) {
                    confirmpassword.requestFocus();
                    confirmpassword.setError("Passwords do not match!!");
                } else if (!crophusbandry.isChecked() && !dairy.isChecked() && !mixed.isChecked()) {
                    Toast.makeText(getActivity(), "Please select a farm category", Toast.LENGTH_LONG).show();
                } else {
                    int checked_id = farm_category.getCheckedRadioButtonId();
                    String category = (String) ((RadioButton) view.findViewById(checked_id)).getText();
                    DatabaseReference farmers = FirebaseDatabase.getInstance().getReference("/farmers");
                    DatabaseReference farms = FirebaseDatabase.getInstance().getReference("/farms");
                    DatabaseReference farmer_child = farmers.push();
                    DatabaseReference farm_child = farms.push();
                    String farmer_key = farmer_child.getKey();
                    String farm_key = farm_child.getKey();

                    Farmer farmer = new Farmer(farmerName, farmercontact, nationID, savedpassword, farm_key);
                    assert farmer_key != null;
                    farmers.child(farmer_key).setValue(farmer);
                    // TODO Add text field for farm location
                    // todo Add regular expressions for the text fields
                    Farm farm = new Farm(farmname, "", category);
                    assert farm_key != null;
                    farms.child(farm_key).setValue(farm);

                    name.setText("");
                    password.setText("");
                    farmername.setText("");
                    confirmpassword.setText("");
                    NIN.setText("");
                    phone.setText("");

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
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    static class Farmer {
        String name;
        String phone;
        String NIN;
        String password;
        String farmkey;

        Farmer(String name, String phone, String NIN, String password, String farmkey) {
            this.name = name;
            this.phone = phone;
            this.NIN = NIN;
            this.password = password;
            this.farmkey = farmkey;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getNIN() {
            return NIN;
        }

        public String getPassword() {
            return password;
        }

        public String getFarmkey() {
            return farmkey;
        }
    }

    static class Farm {
        String name;
        String location;
        String product;

        Farm(String name, String location, String product) {
            this.name = name;
            this.location = location;
            this.product = product;
        }

        public String getName() {
            return name;
        }

        public String getLocation() {
            return location;
        }

        public String getProduct() {
            return product;
        }
    }
}


