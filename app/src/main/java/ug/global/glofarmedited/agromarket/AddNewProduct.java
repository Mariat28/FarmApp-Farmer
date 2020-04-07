package ug.global.glofarmedited.agromarket;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;

public class AddNewProduct extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    TextInputEditText name, description, price;
    ImageView takepicture;
    MaterialButton upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        name = findViewById(R.id.productnameinput);
        description = findViewById(R.id.productdescriptioninput);
        price = findViewById(R.id.productprice);
        upload = findViewById(R.id.upload);
        takepicture = findViewById(R.id.addpicture);
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takepictureintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //Ensure that there's a camera Activity to handle the intent
                if (takepictureintent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takepictureintent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
        newproduct();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            assert data != null;
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap bitmap = (Bitmap) extras.get("data");
            takepicture.setImageBitmap(bitmap);
            takepicture.setEnabled(false);

        }
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
                final String farm_location = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_location", null);
                final String product_category = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("category", null);
                String key = reference.push().getKey();
                takepicture.setDrawingCacheEnabled(true);
                takepicture.buildDrawingCache();
                Bitmap bitmap = ((BitmapDrawable) takepicture.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] data = byteArrayOutputStream.toByteArray();
                String image = Base64.encodeToString(data, Base64.URL_SAFE);
                FirebaseProducts firebaseProducts = new FirebaseProducts(unitprice, desc, productname, product_category, farm_name, farm_location, image);
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
        private String productavailability;
        private String productname;
        private String productcategory;
        private String farmkey;
        private String farmlocation;
        private String image;

        FirebaseProducts(String productprice, String productavailability, String productname, String productcategory, String farmkey, String farmlocation, String image) {
            this.productprice = productprice;
            this.productavailability = productavailability;
            this.productname = productname;
            this.productcategory = productcategory;
            this.farmkey = farmkey;
            this.farmlocation = farmlocation;
            this.image = image;
        }

        public FirebaseProducts() {
        }

        public String getProductprice() {
            return productprice;
        }

        public String getProductavailability() {
            return productavailability;
        }

        public String getProductname() {
            return productname;
        }

        public String getFarmkey() {
            return farmkey;
        }

        public String getFarmlocation() {
            return farmlocation;
        }

        public String getProductcategory() {
            return productcategory;
        }

        public String getImage() {
            return image;
        }
    }

}
