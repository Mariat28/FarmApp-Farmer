package com.example.glosales;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.glosales.AgroMarket.HomeTabbedActivity;
import com.example.glosales.FarmFinancials.FarmFinancialsMain;
import com.example.glosales.SupportTools.Supporttools;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextView farmname, total_stock, sales;
    ImageView sendbutton;
    String retrievedfarmname;
    TextInputEditText openingstock;
    MaterialCardView agromarketcard, farmfinancialscard, supporttoolscard, inputtrackingcard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        farmfinancialscard = findViewById(R.id.farmfinancialscard);
        agromarketcard = findViewById(R.id.agromarket);
        supporttoolscard = findViewById(R.id.supporttoolscard);
        inputtrackingcard = findViewById(R.id.inputtrackingcard);
        agromarketcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeTabbedActivity.class);
                startActivity(intent);
            }
        });
        farmfinancialscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FarmFinancialsMain.class);
                startActivity(intent);

            }
        });
        supporttoolscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Supporttools.class);
                startActivity(intent);


            }
        });
        inputtrackingcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Feature Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });


//        Intent intent = getIntent();
//        retrievedfarmname = intent.getStringExtra("farmname");
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/farmercredentials");
//        farmname = findViewById(R.id.farm_name);
//        Query query = reference.orderByChild("Farm name").equalTo(retrievedfarmname);
//        query.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                String newname = dataSnapshot.child("Farm name").getValue(String.class);
//                //Toast.makeText(getApplicationContext(),""+dataSnapshot.child("Farm name").getValue(String.class),Toast.LENGTH_LONG).show();
//                farmname.setText(newname);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        total_stock = findViewById(R.id.total_stock_view);
//        sales = findViewById(R.id.sales_view);
//        openingstock = findViewById(R.id.openingstock);
//
//        sendbutton = findViewById(R.id.sendbutton);
//        sendbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/Stock_details");
//                final String farmnamee = farmname.getText().toString();
//                String openingstocks = openingstock.getText().toString();
//                DatabaseReference child = reference.push();
//                String key = child.getKey();
//                reference.child(key).child("opening_stock").setValue(openingstocks);
//                reference.child(key).child("farm_name").setValue(farmnamee);
//                openingstock.setText("");
//                Toast.makeText(MainActivity.this, "Opening Stock Added", Toast.LENGTH_SHORT).show();
//                Query query = reference.orderByChild("farm_name");
//                query.addChildEventListener(new ChildEventListener() {
//                    @Override
//                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                        String retrievedfarmname = dataSnapshot.child("farm_name").getValue(String.class);
//                        if (farmnamee.equals(retrievedfarmname)) {
//                            String retrieved_total = dataSnapshot.child("opening_stock").getValue(String.class);
//                            total_stock.setText(retrieved_total);
//                        }
//
//                    }
//
//                    @Override
//                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//                    }
//
//                    @Override
//                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//
//            }
//        });


    }

//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menus, menu);
//
//        return true;
//    }
}
