package ug.global.glofarmedited.agromarket;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;

public class AddNewProduct extends AppCompatActivity {
    TextInputEditText name, description, price;
    MaterialButton upload;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        name = findViewById(R.id.productnameinput);
        description = findViewById(R.id.productdescriptioninput);
        price = findViewById(R.id.productprice);
        upload = findViewById(R.id.upload);
        newproduct();
    }

    public void newproduct() {

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String productname = name.getEditableText().toString().trim();
                final String desc = description.getEditableText().toString().trim();
                final String unitprice = price.getEditableText().toString().trim();

                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/products");
                final String farm_name = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                final String farmerkey = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("firebase_key", null);
                String key = reference.push().getKey();
                FirebaseProducts firebaseProducts = new FirebaseProducts(unitprice, desc, productname, farm_name);
                assert farm_name != null;
                assert key != null;
                reference.child(key).setValue(firebaseProducts);
                reference.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        name.setText("");
                        description.setText("");
                        price.setText("");
                        name.requestFocus();
                        Toast.makeText(AddNewProduct.this, "Product added successfully", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(AddNewProduct.this, "Error occured while adding product", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }

    public static class FirebaseProducts {
        private String productprice;
        private String productdescription;
        private String productname;
        private String farmkey;

        FirebaseProducts(String productprice, String productdescription, String productname, String farmkey) {
            this.productprice = productprice;
            this.productdescription = productdescription;

            this.productname = productname;
            this.farmkey = farmkey;
        }

        public FirebaseProducts() {
        }

        public String getProductprice() {
            return productprice;
        }

        public String getProductdescription() {
            return productdescription;
        }

        public String getProductname() {
            return productname;
        }

        public String getFarmkey() {
            return farmkey;
        }
    }

}
