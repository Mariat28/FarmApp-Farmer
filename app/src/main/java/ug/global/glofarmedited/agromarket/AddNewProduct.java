package ug.global.glofarmedited.agromarket;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;

public class AddNewProduct extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    TextInputEditText name, description, price, units;
    ImageView takepicture;
    MaterialButton upload;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    String selectedunit;
    Uri imageuri;
    String currentphotopath = null;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        name = findViewById(R.id.productnameinput);
        description = findViewById(R.id.productdescriptioninput);
        price = findViewById(R.id.productprice);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        //    units = findViewById(R.id.productunitsinput);
        upload = findViewById(R.id.upload);
        takepicture = findViewById(R.id.addpicture);
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureimage();
            }
        });
        final String product_category = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("category", null);
        assert product_category != null;
        switch (product_category) {
            case "Dairy Farming": {
                Spinner spinner = findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddNewProduct.this, R.array.dairy_units, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedunit = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                break;
            }
            case "Crop Husbandry": {
                Spinner spinner = findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddNewProduct.this, R.array.crop_units, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedunit = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;
            }
            case "Mixed Farming": {
                Spinner spinner = findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddNewProduct.this, R.array.mixed_units, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedunit = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                break;
            }
        }
        //firebaseAuth=FirebaseAuth.getInstance();
        newproduct();
    }

    private File createImageFile() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imagefilename = "JPEG" + timestamp + "_";
        File storagedirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imagefilename, ".jpg", storagedirectory);
        currentphotopath = image.getAbsolutePath();
        return image;
    }

    public void captureimage() {
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraintent.resolveActivity(getPackageManager()) != null) {
            File imagefile = null;
            try {
                imagefile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (imagefile != null) {
                imageuri = FileProvider.getUriForFile(this, "ug.global.android.fileprovider", imagefile);
                cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                startActivityForResult(cameraintent, REQUEST_IMAGE_CAPTURE);
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(currentphotopath);
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
                // final String productunits = units.getEditableText().toString().trim();
                if (takepicture.getDrawable() == null) {
                    Toast.makeText(AddNewProduct.this, "Please Take Product image to proceed", Toast.LENGTH_SHORT).show();
                    upload.setEnabled(false);
                } else if (productname.length() == 0) {
                    name.requestFocus();
                    name.setError("Enter product name");
                } else if (desc.length() == 0) {
                    description.requestFocus();
                    description.setError("Enter available product amount");

                } else if (unitprice.length() == 0) {
                    price.requestFocus();
                    price.setError("Enter product unit cost");
                } else {
                    final ProgressDialog progressDialog = new ProgressDialog(AddNewProduct.this);
                    progressDialog.setMessage("Uploading product data...Please wait");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    final String farm_name = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                    final String farm_location = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_location", null);
                    final String product_category = getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("category", null);
                    final String userid = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();
                    storageReference = firebaseStorage.getReference();
                    final StorageReference imagestorage = storageReference.child("product_pictures").child(userid).child(Objects.requireNonNull(imageuri.getLastPathSegment()));
                    UploadTask uploadTask = imagestorage.putFile(imageuri);
                    uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw Objects.requireNonNull(task.getException());
                            }
                            return imagestorage.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {

                                String pdtimageurl = Objects.requireNonNull(task.getResult()).toString();
                                Log.i("TAG", "productUrl" + pdtimageurl);
                                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/products");
                                String key = reference.push().getKey();
                                final int availableamount = Integer.parseInt(desc);
                                assert product_category != null;
                                FirebaseProducts firebaseProducts = new FirebaseProducts(unitprice, userid, availableamount, productname, product_category, farm_name, farm_location, selectedunit, pdtimageurl, key);
                                assert farm_name != null;
                                assert key != null;
                                reference.child(key).setValue(firebaseProducts);
                                reference.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        progressDialog.dismiss();
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


                        }
                    });


                }

            }
        });

    }

    public static class FirebaseProducts {
        private String productprice;
        private String userid;
        private int productavailability;
        private String productname;
        private String productcategory;
        private String farmkey;
        private String farmlocation;
        private String productunit;
        private String image;
        private String productkey;

        FirebaseProducts(String productprice, String userid, int productavailability, String productname, String productcategory, String farmkey, String farmlocation, String productunit, String image, String productkey) {
            this.productprice = productprice;
            this.userid = userid;
            this.productavailability = productavailability;
            this.productname = productname;
            this.productcategory = productcategory;
            this.farmkey = farmkey;
            this.farmlocation = farmlocation;
            this.productunit = productunit;
            this.image = image;
            this.productkey = productkey;
        }

        public FirebaseProducts() {
        }

        public String getProductprice() {
            return productprice;
        }

        public int getProductavailability() {
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

        public String getProductunit() {
            return productunit;
        }

        public String getProductkey() {
            return productkey;
        }

        public String getUserid() {
            return userid;
        }
    }

}
