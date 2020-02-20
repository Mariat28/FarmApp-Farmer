package com.example.glosales.agromarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.MarketObject;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MyViewHolder> {
    private LayoutInflater layoutInflater;
    private ArrayList<MarketObject> marketObjectArrayList;
    private Context context;

    public MarketAdapter(ArrayList<MarketObject> marketObjectArrayList, Context context) {
        this.marketObjectArrayList = marketObjectArrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.market_row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(marketObjectArrayList.get(position).getFarmname());
        holder.contact.setText(marketObjectArrayList.get(position).getContact());
        holder.location.setText(marketObjectArrayList.get(position).getLocation());
        holder.photo.setImageResource(marketObjectArrayList.get(position).getPhoto());
        holder.marketcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Farmer Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return marketObjectArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, location;
        ImageView photo;
        MaterialCardView marketcard;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.farmername);
            contact = itemView.findViewById(R.id.farmercontact);
            location = itemView.findViewById(R.id.location);
            photo = itemView.findViewById(R.id.photo);
            marketcard = itemView.findViewById(R.id.marketcard);
        }
    }
}
