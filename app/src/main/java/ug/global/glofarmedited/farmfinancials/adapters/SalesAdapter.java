package ug.global.glofarmedited.farmfinancials.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.farmfinancials.adapterobjects.SalesObjects;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.MyViewHolder> {
    private ArrayList<SalesObjects> salesObjectsArrayList;
    private LayoutInflater layoutInflater;

    public SalesAdapter(Context context, ArrayList<SalesObjects> salesObjectsArrayList) {
        this.salesObjectsArrayList = salesObjectsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.sales_row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(salesObjectsArrayList.get(position).getName());
        holder.price.setText(salesObjectsArrayList.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return salesObjectsArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.salesproductname);
            price = itemView.findViewById(R.id.salesproductamount);


        }
    }
}
