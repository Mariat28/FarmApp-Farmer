package com.example.glosales.agromarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.agromarket.adapterobjects.SupplierObjects;

import java.util.ArrayList;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.ViewHolder> {
    private ArrayList<SupplierObjects> supplierObjectsArrayList;
    private LayoutInflater layoutInflater;

    public SupplierAdapter(ArrayList<SupplierObjects> supplierObjectsArrayList, Context context) {
        this.supplierObjectsArrayList = supplierObjectsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.supplies_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(supplierObjectsArrayList.get(position).getSuppliername());
        holder.location.setText(supplierObjectsArrayList.get(position).getSupplierlocation());
        holder.contact.setText(supplierObjectsArrayList.get(position).getSuppliercontact());
        holder.photo.setImageResource(supplierObjectsArrayList.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return supplierObjectsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, location, contact;
        ImageView photo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.suppliername);
            location = itemView.findViewById(R.id.supplierlocation);
            contact = itemView.findViewById(R.id.suppliercontact);
            photo = itemView.findViewById(R.id.farmlogo);
        }
    }
}
