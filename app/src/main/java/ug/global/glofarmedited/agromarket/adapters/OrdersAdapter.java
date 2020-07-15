package ug.global.glofarmedited.agromarket.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.ProductsActivityMain;
import ug.global.glofarmedited.agromarket.SalesObjects;
import ug.global.glofarmedited.agromarket.adapterobjects.OrderObject;

import static android.content.Context.MODE_PRIVATE;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private ArrayList<OrderObject> orderObjectArrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public OrdersAdapter(ArrayList<OrderObject> orderObjectArrayList, Context context) {
        this.orderObjectArrayList = orderObjectArrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.orders_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.clientname.setText(orderObjectArrayList.get(position).getShopname());
        holder.productname.setText(orderObjectArrayList.get(position).getProductname());
        holder.quantity.setText(orderObjectArrayList.get(position).getQuantity());
        holder.cost.setText(orderObjectArrayList.get(position).getOrderamount());
        holder.timestamp.setText(orderObjectArrayList.get(position).getTimestamp());
        holder.location.setText(orderObjectArrayList.get(position).getShoplocation());
        holder.ordercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((holder.status.getText().toString()).equals("confirmed")) {
                    Toast.makeText(context, "Order Already Confirmed", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog builder = new AlertDialog.Builder(context).create();
                    builder.setTitle("Confirm Order");
                    builder.setButton(DialogInterface.BUTTON_POSITIVE, "CONFIRM ORDER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            final int position = holder.getAdapterPosition();
                            if (position != -1) {

                                // OrderObject selectedorder=orderObjectArrayList.get(position);
                                final String clientname = holder.clientname.getText().toString();
                                final String productname = holder.productname.getText().toString();
                                final String quantity = holder.quantity.getText().toString();
                                long cost = Long.parseLong((holder.cost.getText().toString()));
                                final String time = holder.timestamp.getText().toString().trim();
                                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("/farms");
                                final String farm_name = context.getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
                                final String key = databaseReference.push().getKey();
                                SalesObjects salesObjects = new SalesObjects(productname, cost, time, quantity, farm_name);
                                assert key != null;
                                assert farm_name != null;
                                databaseReference.child(farm_name).child("sales").child(key).setValue(salesObjects);

                                final DatabaseReference orderref = FirebaseDatabase.getInstance().getReference("/orders");
                                Query query = orderref.orderByChild("farmname").equalTo(farm_name);
                                query.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        String key = dataSnapshot.child("orderkey").getValue(String.class);
                                        assert key != null;
                                        orderObjectArrayList.remove(position);
                                        notifyItemRemoved(position);
                                        orderref.child(key).removeValue();
                                        Toast.makeText(context, "Order Confirmed, and added as a Sale", Toast.LENGTH_SHORT).show();
                                        ProgressDialog progressDialog = new ProgressDialog(context);
                                        progressDialog.setMessage("Updating Order List");
                                        progressDialog.show();
                                        context.startActivity(new Intent(context, ProductsActivityMain.class));
                                        progressDialog.dismiss();
                                        ((Activity) context).finish();

                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                                        String key = dataSnapshot.child("orderkey").getValue(String.class);
                                       /* assert key != null;
                                        orderObjectArrayList.remove(position);
                                        notifyItemRemoved(position);*/
                                        assert key != null;
                                        orderref.child(key).removeValue();/*
                                        context.startActivity(new Intent(context, ProductsActivityMain.class));
                                        ((Activity)context).finish();*/

                                        //context.startActivity(new Intent(context, ProductsActivityMain.class));

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }


                        }
                    });

                    builder.setButton(DialogInterface.BUTTON_NEGATIVE, "CALL CLIENT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                        }
                    });
                    builder.show();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return
                orderObjectArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView clientname, productname, quantity, cost, timestamp, location, status;
        CardView ordercard;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clientname = itemView.findViewById(R.id.clientname);
            productname = itemView.findViewById(R.id.productname);
            quantity = itemView.findViewById(R.id.quantity);
            cost = itemView.findViewById(R.id.productcost);
            timestamp = itemView.findViewById(R.id.timestamp);
            location = itemView.findViewById(R.id.location);
            ordercard = itemView.findViewById(R.id.ordercard);
            status = itemView.findViewById(R.id.status);


        }


    }

}
