package com.example.glosales.agromarket.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.FarmToolsObjects;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class FarmToolsAdapter extends RecyclerView.Adapter<FarmToolsAdapter.MyViewHolder> {
    private ArrayList<FarmToolsObjects> farmToolsObjectsArrayList;
    private LayoutInflater layoutInflater;
    private Context context;

    public FarmToolsAdapter(ArrayList<FarmToolsObjects> farmToolsObjectsArrayList, Context context) {
        this.farmToolsObjectsArrayList = farmToolsObjectsArrayList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FarmToolsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.tools_row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmToolsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(farmToolsObjectsArrayList.get(position).getName());
        holder.price.setText(farmToolsObjectsArrayList.get(position).getPrice());
        holder.available.setText(farmToolsObjectsArrayList.get(position).getAvailable());
        holder.toolscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new MaterialAlertDialogBuilder(context).create();
                builder.setTitle("CALL TO PLACE YOUR ORDER");
                builder.setButton(DialogInterface.BUTTON_POSITIVE, "CALL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
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
        return farmToolsObjectsArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, available;
        ImageView photo, calldealer;
        CardView toolscard;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.toolname);
            price = itemView.findViewById(R.id.toolprice);
            photo = itemView.findViewById(R.id.dealerimage);
            calldealer = itemView.findViewById(R.id.productimage);
            available = itemView.findViewById(R.id.availabletools);
            toolscard = itemView.findViewById(R.id.toolscard);


        }


    }
}