package ug.global.glofarmedited.agromarket.adapters;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.adapterobjects.SupplierObjects;

import static androidx.core.content.ContextCompat.startActivity;

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
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.name.setText(supplierObjectsArrayList.get(position).getProductname());
        holder.price.setText(supplierObjectsArrayList.get(position).getPrice());
        holder.availability.setText(supplierObjectsArrayList.get(position).getAvailablesupply());
        holder.photo.setImageResource(supplierObjectsArrayList.get(position).getPhoto());

    }

    @Override
    public int getItemCount() {
        return supplierObjectsArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, availability;
        ImageView photo;
        CardView supplycard;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.productname);
            availability = itemView.findViewById(R.id.availablesupply);
            price = itemView.findViewById(R.id.supplyprice);
            photo = itemView.findViewById(R.id.productphoto);
            supplycard = itemView.findViewById(R.id.marketcard);
            supplycard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(supplycard.getContext(), "Call To Place Order", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(supplycard.getContext());
                    builder.setMessage("CALL 0705976941 to place order");
                    builder.setPositiveButton("CALL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("0705976941"));

                            if (ActivityCompat.checkSelfPermission(supplycard.getContext(),
                                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(supplycard.getContext(), intent, null);


                        }
                    });
                    builder.create();
                    builder.show();
                }
            });
        }
    }
}
