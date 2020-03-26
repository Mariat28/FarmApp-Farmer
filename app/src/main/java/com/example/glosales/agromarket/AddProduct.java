package com.example.glosales.agromarket;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.glosales.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddProduct extends AppCompatActivity {
    ImageView addproduct;
    TextInputEditText productname, description;
    MaterialButton upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);
        Toolbar toolbar = findViewById(R.id.productdetailstoolbar);
        toolbar.setTitle("Add New Product");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        addproduct = findViewById(R.id.addpicture);
        productname = findViewById(R.id.productnameinput);
        description = findViewById(R.id.productdescriptioninput);
        upload = findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productnames = productname.getEditableText().toString();
                String pdtdesc = description.getEditableText().toString();


            }
        });
    }

    static class Products {
        private String name, desc, key;

        Products(String name, String desc, String key) {
            this.name = name;
            this.desc = desc;
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public String getKey() {
            return key;
        }
    }

}

