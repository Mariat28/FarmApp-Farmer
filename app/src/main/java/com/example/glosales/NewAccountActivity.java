package com.example.glosales;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class NewAccountActivity extends AppCompatActivity {

    Context context;
    private TextInputEditText password, name, farmername, confirmpassword, NIN, phone, location;
    private RadioButton crophusbandry, mixed, dairy;
    private RadioGroup farm_category;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        password = findViewById(R.id.password);
        name = findViewById(R.id.farm_name);
        farmername = findViewById(R.id.farmnamee);
        location = findViewById(R.id.famlocation);
        confirmpassword = findViewById(R.id.passwordconfirm);
        NIN = findViewById(R.id.NIN);
        phone = findViewById(R.id.phonenumber);
        progressBar = findViewById(R.id.progressBar2);

        crophusbandry = findViewById(R.id.crops);
        mixed = findViewById(R.id.mixed);
        dairy = findViewById(R.id.dairy);
        farm_category = findViewById(R.id.productcategories);
        Button signIn = findViewById(R.id.signinbutton);
        progressBar.setVisibility(View.INVISIBLE);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                newuser();
            }
        });
    }

    private void newuser() {
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
            Toast.makeText(NewAccountActivity.this, "Please select a farm category", Toast.LENGTH_LONG).show();
        } else {
            int checked_id = farm_category.getCheckedRadioButtonId();
            final String category = (String) ((RadioButton) findViewById(checked_id)).getText();
            progressBar.setVisibility(View.INVISIBLE);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmerdetails");
            DatabaseReference credentials = FirebaseDatabase.getInstance().getReference("/farmercredentials");
            DatabaseReference farmers = reference.push();
            DatabaseReference farmercredentials = credentials.push();
            String key = farmers.getKey();
            CreateAccount.Farmer farmer = new CreateAccount.Farmer(farmercontact, nationID, farmname, farmlocation, category, key);
            assert key != null;
            reference.child(key).setValue(farmer);
            CreateAccount.Credentials credentials1 = new CreateAccount.Credentials(farmerName, savedpassword);
            farmercredentials.child(key).setValue(credentials1);
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
            Toast.makeText(NewAccountActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NewAccountActivity.this, MainActivity.class);
            intent.putExtra("farmkey", key);
            startActivity(intent);
        }
    }
}
