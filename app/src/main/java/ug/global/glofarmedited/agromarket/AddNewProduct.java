package ug.global.glofarmedited.agromarket;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ug.global.glofarmedited.R;

public class AddNewProduct extends AppCompatActivity {
    TextInputEditText name, description;
    MaterialButton upload;
    FirebaseAuth firebaseAuth;
    String userid;

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.getCurrentUser();
            newproduct();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        name = findViewById(R.id.productnameinput);
        description = findViewById(R.id.productdescriptioninput);
        upload = findViewById(R.id.upload);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    public void newproduct() {

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productname = name.getEditableText().toString();
                String desc = description.getEditableText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmers");
                String id = reference.push().getKey();
                assert id != null;
                reference.child(id).child("products").setValue(productname);
                reference.child(id).child("products").setValue(desc);
                Toast.makeText(AddNewProduct.this, "Product Added", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
