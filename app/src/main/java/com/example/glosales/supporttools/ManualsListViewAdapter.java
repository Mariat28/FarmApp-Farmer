package com.example.glosales.supporttools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.glosales.R;

import java.util.ArrayList;

public class ManualsListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewObjects> listViewObjectsArrayList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ManualsListViewAdapter(ArrayList<ListViewObjects> listViewObjectsArrayList, Context context) {
        this.listViewObjectsArrayList = listViewObjectsArrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listViewObjectsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        convertView = layoutInflater.inflate(R.layout.manuals_list_row_item, parent, false);
        holder = new ViewHolder();
        holder.manualname = convertView.findViewById(R.id.name);
        holder.manualdate = convertView.findViewById(R.id.date);
        convertView.setTag(holder);


        holder.manualname.setText(listViewObjectsArrayList.get(position).getManualname());
        holder.manualdate.setText(listViewObjectsArrayList.get(position).getManualdate());


        return convertView;
    }

    static class ViewHolder {
        TextView manualname, manualdate;


    }
}
