package ug.global.glofarmedited.agromarket.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.adapterobjects.ProductObjects;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {
    private ArrayList<ProductObjects> productObjectsArrayList;
    private LayoutInflater layoutInflater;

    public ProductsAdapter(ArrayList<ProductObjects> productObjectsArrayList, Context context) {
        this.productObjectsArrayList = productObjectsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.product_list_row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(productObjectsArrayList.get(position).getProductname());
        holder.desc.setText(productObjectsArrayList.get(position).getProductdescription());
        holder.price.setText(productObjectsArrayList.get(position).getProductprice());

    }

    @Override
    public int getItemCount() {
        return productObjectsArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc, price;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            desc = itemView.findViewById(R.id.productdescription);
            name = itemView.findViewById(R.id.productname);
            price = itemView.findViewById(R.id.productprices);

        }
    }
}
