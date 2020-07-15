package ug.global.glofarmedited.supporttools.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;

public class ManualsRecyclerViewAdapter extends RecyclerView.Adapter<ManualsRecyclerViewAdapter.MyViewHolder> {
    private ArrayList<ListViewObjects> listViewObjectsArrayList;
    private LayoutInflater layoutInflater;

    public ManualsRecyclerViewAdapter(ArrayList<ListViewObjects> listViewObjectsArrayList, Context context) {
        this.listViewObjectsArrayList = listViewObjectsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.manuals_list_row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.question.setText(listViewObjectsArrayList.get(position).getQuestion());
        holder.answer.setText(listViewObjectsArrayList.get(position).getAnswer());

    }

    @Override
    public int getItemCount() {
        return listViewObjectsArrayList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView question, answer;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);


        }
    }
}
