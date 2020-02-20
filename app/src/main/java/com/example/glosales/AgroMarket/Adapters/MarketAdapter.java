package com.example.glosales.AgroMarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.AgroMarket.AdapterClasses.marketClass;
import com.example.glosales.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MyViewHolder> {
    LayoutInflater layoutInflater;
    private ArrayList<marketClass> marketClassArrayList;
    private Context context;

    public MarketAdapter(ArrayList<marketClass> marketClassArrayList, Context context) {
        this.marketClassArrayList = marketClassArrayList;
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
        holder.name.setText(marketClassArrayList.get(position).getFarmname());
        holder.contact.setText(marketClassArrayList.get(position).getContact());
        holder.location.setText(marketClassArrayList.get(position).getLocation());
        holder.photo.setImageResource(marketClassArrayList.get(position).getPhoto());
        holder.marketcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Farmer Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return marketClassArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, contact, location;
        ImageView photo;
        MaterialCardView marketcard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.farmername);
            contact = itemView.findViewById(R.id.farmercontact);
            location = itemView.findViewById(R.id.location);
            photo = itemView.findViewById(R.id.photo);
            marketcard = itemView.findViewById(R.id.marketcard);
        }
    }
}
