package com.example.glosales.agromarket;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.glosales.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Productdetails extends AppCompatActivity {
    ImageView addproduct;
    TextInputEditText productname, description;
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        Toolbar toolbar = findViewById(R.id.productdetailstoolbar);
        toolbar.setTitle("Add New Product");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        addproduct = findViewById(R.id.addpicture);
        upload = findViewById(R.id.upload);
    }

}

