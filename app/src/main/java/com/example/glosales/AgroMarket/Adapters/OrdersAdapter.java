package com.example.glosales.AgroMarket.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.AgroMarket.AdapterClasses.Orderclass;
import com.example.glosales.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private ArrayList<Orderclass> orderclass;
    private LayoutInflater layoutInflater;
    private Context context;

    public OrdersAdapter(ArrayList<Orderclass> orderclass, Context context) {
        this.orderclass = orderclass;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.orders_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(orderclass.get(position).getClientname());
        holder.contact.setText(orderclass.get(position).getClientcontact());
        holder.details.setText(orderclass.get(position).getOrderdetails());
        holder.ordercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new AlertDialog.Builder(context).create();
                builder.setTitle("Confirm Order");
                builder.setButton(DialogInterface.BUTTON_POSITIVE, "CONFIRM ORDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setButton(DialogInterface.BUTTON_NEGATIVE, "CALL CLIENT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return
                orderclass.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, details;
        CardView ordercard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.clientname);
            contact = itemView.findViewById(R.id.clientcontact);
            details = itemView.findViewById(R.id.orderdetails);
            ordercard = itemView.findViewById(R.id.ordercard);


        }


    }

}
