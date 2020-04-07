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
import ug.global.glofarmedited.farmfinancials.adapterobjects.InputObjects;

public class InputsAdapter extends RecyclerView.Adapter<InputsAdapter.MyViewHolder> {
    private ArrayList<InputObjects> inputObjectsArrayList;
    private LayoutInflater layoutInflater;

    public InputsAdapter(ArrayList<InputObjects> inputObjectsArrayList, Context context) {
        this.inputObjectsArrayList = inputObjectsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.expenses_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(inputObjectsArrayList.get(position).getInputname());
        holder.amount.setText(inputObjectsArrayList.get(position).getInputcost());

    }

    @Override
    public int getItemCount() {
        return inputObjectsArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Expensename);
            amount = itemView.findViewById(R.id.Expenseamount);
        }
    }
}
