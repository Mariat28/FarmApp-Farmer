package ug.global.glofarmedited;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NewAccountActivity extends AppCompatActivity {
    private static String userId;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth.AuthStateListener authStateListener;
    TextInputEditText password, name, farmername, confirmpassword, NIN, phone, location, lastname;
    RadioButton crophusbandry, mixed, dairy;
    RadioGroup farm_category;
    Button create;
    TextInputEditText[] textInputEditTexts;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseauthentication();
        setContentView(R.layout.activity_new_account);
        password = findViewById(R.id.password);
        name = findViewById(R.id.farm_name);
        farmername = findViewById(R.id.farmnamee);
        lastname = findViewById(R.id.lastfarmname);
        location = findViewById(R.id.famlocation);
        confirmpassword = findViewById(R.id.passwordconfirm);
        NIN = findViewById(R.id.NIN);
        phone = findViewById(R.id.phonenumber);
        crophusbandry = findViewById(R.id.crops);
        mixed = findViewById(R.id.mixed);
        dairy = findViewById(R.id.dairy);
        farm_category = findViewById(R.id.productcategories);
        create = findViewById(R.id.signinbutton);
        password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        NIN.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        NIN.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)});


        confirmpassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        textInputEditTexts = new TextInputEditText[]{password, name, farmername, location, confirmpassword, NIN, phone};


        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                newuser();

            }

        });


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void newuser() {


      /*  for (TextInputEditText textInputEditText : textInputEditTexts) {
            textInputEditText.setEnabled(false);
        }*/
        final String savedpassword = (password.getEditableText()).toString();
        final String farmlocation = location.getEditableText().toString();
        final String farmname = name.getEditableText().toString();
        final String lastfarmername = lastname.getEditableText().toString();
        final String farmerName = farmername.getEditableText().toString();
        String confirmedpassword = confirmpassword.getEditableText().toString();
        final String nationID = NIN.getEditableText().toString();
        final String farmercontact = phone.getEditableText().toString();
        boolean islowercase = !nationID.equals(nationID.toUpperCase());

        if (farmerName.length() == 0) {
            farmername.requestFocus();
            farmername.setError("Please enter your name");
        } else if (lastfarmername.length() == 0) {
            lastname.requestFocus();
            lastname.setError("Please enter your name");
        } else if (farmname.length() == 0) {
            name.requestFocus();
            name.setError("Please enter a farm Name ");
        } else if (nationID.length() == 0) {
            NIN.requestFocus();
            NIN.setError("Please enter your National ID number");
        } else if (nationID.length() < 14) {
            NIN.requestFocus();
            NIN.setError(" National ID number should be 14 characters");
        } else if (islowercase) {
            NIN.setText(nationID);
            NIN.requestFocus();
            NIN.setError("Make sure the NIN is in capital letters");
        } else if (farmercontact.length() == 0) {
            phone.requestFocus();
            phone.setError("Please enter your telephone number");
        } else if (farmercontact.length() < 10) {
            phone.requestFocus();
            phone.setError("Please enter the correct telephone number");
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
            Toast.makeText(NewAccountActivity.this, "Please select a farm category", Toast.LENGTH_LONG).show();
        } else {
            progressDialog = new ProgressDialog(NewAccountActivity.this);
            progressDialog.setMessage("Creating Account...Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //final DatabaseReference farmersRef = FirebaseDatabase.getInstance().getReference("/farmers");
            final DatabaseReference farmsRef = FirebaseDatabase.getInstance().getReference("/farms");
            int checked_id = farm_category.getCheckedRadioButtonId();
            final String category = (String) ((RadioButton) findViewById(checked_id)).getText();
            final String firebaseKeyfarmer = (farmerName + nationID).replace(" ", "_");
            final String farmerkey = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
            String firebaseKeyfarm = (farmname + farmlocation).replace(" ", "_");

            Farmer farmer = new Farmer(farmerName + "" + lastfarmername, farmercontact, nationID, firebaseKeyfarm, farmlocation, category, savedpassword, farmerkey);
            Farm farm = new Farm(farmname, category);
            // farmsRef.child(firebaseKeyfarm).setValue(farm);
            farmsRef.child(farmname).child("profile").setValue(farmer);
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("is_registered", true);
            editor.putString("pin", savedpassword);
            editor.putString("firebase_key", firebaseKeyfarmer);
            editor.putString("farm_name", farmname);
            editor.putString("farm_location", farmlocation);
            editor.putString("category", category);
            editor.apply();
            farmsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressDialog.dismiss();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(NewAccountActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    for (TextInputEditText textInputEditText : textInputEditTexts) {
                        textInputEditText.setEnabled(true);
                    }

                }
            });


        }
    }

    static class Farm {
        public Farm() {
        }

        String farmname;
        String farmcategory;

        Farm(String farmname, String farmcategory) {
            this.farmname = farmname;
            this.farmcategory = farmcategory;
        }

        public String getFarmname() {
            return farmname;
        }

        public String getFarmcategory() {
            return farmcategory;
        }
    }

    static class Farmer {
        String name;
        String phone;
        String NIN;
        String farmname;
        String farmlocation;
        String category;
        String password;
        String id;

        public Farmer() {

        }

        Farmer(String name, String phone, String NIN, String farmname, String farmlocation, String category, String password, String id) {
            this.name = name;
            this.phone = phone;
            this.NIN = NIN;
            this.farmname = farmname;
            this.farmlocation = farmlocation;
            this.category = category;
            this.password = password;
            this.id = id;
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

        public String getFarmname() {
            return farmname;
        }

        public String getFarmlocation() {
            return farmlocation;
        }

        public String getCategory() {
            return category;
        }

        public String getPassword() {
            return password;
        }

        public String getId() {
            return id;
        }


    }

    private void firebaseauthentication() {


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    // Choose authentication providers
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.PhoneBuilder().build());

// Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            1);


                }
                Toast.makeText(NewAccountActivity.this, "Welcome Back", Toast.LENGTH_SHORT).show();

            }
        };
    }


    @Override
    protected void onResume() {
        super.onResume();
        //attach listener
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //detach listener
        firebaseAuth.removeAuthStateListener(authStateListener);
    }
}


