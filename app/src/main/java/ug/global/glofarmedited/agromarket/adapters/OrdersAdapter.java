package ug.global.glofarmedited.agromarket.adapters;

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

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.adapterobjects.OrderObject;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private ArrayList<OrderObject> orderObjects;
    private LayoutInflater layoutInflater;
    private Context context;

    public OrdersAdapter(ArrayList<OrderObject> orderObjects, Context context) {
        this.orderObjects = orderObjects;
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
        holder.clientname.setText(orderObjects.get(position).getShopname());
        holder.productname.setText(orderObjects.get(position).getProductname());
        holder.quantity.setText(orderObjects.get(position).getQuantity());
        holder.cost.setText(orderObjects.get(position).getOrderamount());
        holder.timestamp.setText(orderObjects.get(position).getTimestamp());
        holder.location.setText(orderObjects.get(position).getShoplocation());
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
                orderObjects.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView clientname, productname, quantity, cost, timestamp, location;
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


        }


    }

}
