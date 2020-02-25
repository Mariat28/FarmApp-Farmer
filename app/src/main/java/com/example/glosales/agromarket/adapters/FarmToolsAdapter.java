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

        return new FarmToolsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmToolsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(farmToolsObjectsArrayList.get(position).getDealername());
        holder.contact.setText(farmToolsObjectsArrayList.get(position).getDealercontact());
        holder.photo.setImageResource(farmToolsObjectsArrayList.get(position).getDealerphoto());
        holder.calldealer.setImageResource(farmToolsObjectsArrayList.get(position).getDealercall());
        holder.details.setText(farmToolsObjectsArrayList.get(position).getDealerdetails());
        holder.toolscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new MaterialAlertDialogBuilder(context).create();
                builder.setTitle("Contact Dealer");
                builder.setButton(DialogInterface.BUTTON_POSITIVE, "CALL DEALER", new DialogInterface.OnClickListener() {
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, details;
        ImageView photo, calldealer;
        CardView toolscard;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.dealername);
            contact = itemView.findViewById(R.id.dealercontact);
            photo = itemView.findViewById(R.id.dealerimage);
            calldealer = itemView.findViewById(R.id.productimage);
            details = itemView.findViewById(R.id.dealerdetails);
            toolscard = itemView.findViewById(R.id.toolscard);


        }


    }
}