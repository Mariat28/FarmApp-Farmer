package com.example.glosales.AgroMarket.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.AgroMarket.AdapterClasses.PlaceObject;
import com.example.glosales.R;

import java.util.ArrayList;

public class MyProductsAdapter extends RecyclerView.Adapter<MyProductsAdapter.MyViewHolder> {
    private android.view.LayoutInflater LayoutInflater;
    private ArrayList<Integer> productImages;
    private Context context;


    private ArrayList<PlaceObject> placeObjects;

    public MyProductsAdapter(Context context, ArrayList<PlaceObject> placeObjects) {
        this.LayoutInflater = android.view.LayoutInflater.from(context);
        this.context = context;
        this.placeObjects = placeObjects;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.inflate(R.layout.recyclerviewlayout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.description.setText(placeObjects.get(position).getDescription());
        holder.name.setText(placeObjects.get(position).getName());
        /*holder.productimage;*/
        /*holder.productimage.setImageDrawable(context.getResources().getDrawable(productImages.get(position)));*/


    }

    @Override
    public int getItemCount() {
        return placeObjects.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        ImageView productimage;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            productimage = itemView.findViewById(R.id.productimage);

        }
    }
}

