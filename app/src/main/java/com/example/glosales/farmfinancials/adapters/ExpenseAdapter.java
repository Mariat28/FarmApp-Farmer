package com.example.glosales.farmfinancials.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;
import com.example.glosales.farmfinancials.ExpenseClass;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    private ArrayList<ExpenseClass> expenseClass;
    private Context context;
    private LayoutInflater layoutInflater;

    public ExpenseAdapter(Context context, ArrayList<ExpenseClass> expenseClass) {
        this.expenseClass = expenseClass;
        this.context = context;
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
        holder.name.setText(expenseClass.get(position).getExpense_name());
        holder.amount.setText(expenseClass.get(position).getExpense_amount());
    }

    @Override
    public int getItemCount() {
        return expenseClass.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Expensename);
            amount = itemView.findViewById(R.id.Expenseamount);
        }
    }
}
