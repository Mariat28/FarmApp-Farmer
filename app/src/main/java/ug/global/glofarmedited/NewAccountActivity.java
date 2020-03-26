package ug.global.glofarmedited;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewAccountActivity extends AppCompatActivity {
    private static String userId;
    Context context;
    TextInputEditText password, name, farmername, confirmpassword, NIN, phone, location;
    RadioButton crophusbandry, mixed, dairy;
    RadioGroup farm_category;
    Button create;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = findViewById(R.id.password);
        name = findViewById(R.id.farm_name);
        farmername = findViewById(R.id.farmnamee);
        location = findViewById(R.id.famlocation);
        confirmpassword = findViewById(R.id.passwordconfirm);
        NIN = findViewById(R.id.NIN);
        phone = findViewById(R.id.phonenumber);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        crophusbandry = findViewById(R.id.crops);
        mixed = findViewById(R.id.mixed);
        dairy = findViewById(R.id.dairy);
        farm_category = findViewById(R.id.productcategories);
        create = findViewById(R.id.signinbutton);

        create.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                newuser();

            }

        });


    }
  /*  @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent=new Intent(this,Home.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }*/

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void newuser() {
        final String savedpassword = (password.getEditableText()).toString();
        final String farmlocation = location.getEditableText().toString();
        final String farmname = name.getEditableText().toString();
        final String farmerName = farmername.getEditableText().toString();
        String confirmedpassword = confirmpassword.getEditableText().toString();
        final String nationID = NIN.getEditableText().toString();
        final String farmercontact = phone.getEditableText().toString();

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
            Toast.makeText(NewAccountActivity.this, "Please select a farm category", Toast.LENGTH_LONG).show();
        } else {
            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/farmers");
            int checked_id = farm_category.getCheckedRadioButtonId();
            final String category = (String) ((RadioButton) findViewById(checked_id)).getText();
            String id = databaseReference.push().getKey();
            Farmer farmer = new Farmer(farmerName, farmercontact, nationID, farmname, farmlocation, category, savedpassword, id);
            assert id != null;
            databaseReference.child(id).setValue(farmer);
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    name.setText("");
                    password.setText("");
                    farmername.setText("");
                    confirmpassword.setText("");
                    NIN.setText("");
                    phone.setText("");
                    location.setText("");
                    mixed.setChecked(false);
                    dairy.setChecked(false);
                    crophusbandry.setChecked(false);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(NewAccountActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NewAccountActivity.this, Home.class);
                    startActivity(intent);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(NewAccountActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();


                }
            });


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

}
