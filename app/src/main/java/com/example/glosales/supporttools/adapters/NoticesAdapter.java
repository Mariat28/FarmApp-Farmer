package com.example.glosales.supporttools.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glosales.R;

import java.util.ArrayList;

public class NoticesAdapter extends RecyclerView.Adapter<NoticesAdapter.MyViewHolder> {
    private ArrayList<NoticesObjects> noticesObjectsArrayList;
    private LayoutInflater layoutInflater;

    public NoticesAdapter(ArrayList<NoticesObjects> noticesObjectsArrayList, Context context) {
        this.noticesObjectsArrayList = noticesObjectsArrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.notices_row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(noticesObjectsArrayList.get(position).getNoticetitle());
        holder.date.setText(noticesObjectsArrayList.get(position).getDate());
        holder.description.setText(noticesObjectsArrayList.get(position).getNoticedesc());
    }

    @Override
    public int getItemCount() {
        return noticesObjectsArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date, title, description;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.noticedate);
            title = itemView.findViewById(R.id.noticetitle);
            description = itemView.findViewById(R.id.noticedesc);

        }
    }
}
