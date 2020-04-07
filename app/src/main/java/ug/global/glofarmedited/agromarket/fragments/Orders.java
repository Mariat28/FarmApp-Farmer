package ug.global.glofarmedited.agromarket.fragments;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import ug.global.glofarmedited.Constants;
import ug.global.glofarmedited.R;
import ug.global.glofarmedited.agromarket.ProductsActivityMain;
import ug.global.glofarmedited.agromarket.adapterobjects.OrderObject;
import ug.global.glofarmedited.agromarket.adapters.OrdersAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Orders extends Fragment {
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "key=" + "AAAA56YjnQc:APA91bGZc6gm_o2LMyXD4yBz_I9EccEM2Px53fNe-Pvytyz1oC7E4-DWvaFkSbs_Sg4uEcEGrtW1ttshyb6Z5yvAte33pNGstriuKSg8AFcQX1H80Ro5bbwwhi4j3UhFNkAqjA9Phdqa";
    final private String contentType = "application/json";
    private final String TAG = "NOTIFICATION TAG";
    private String NOTIFICATION_TITLE;
    private String NOTIFICATION_MESSAGE;
    private String TOPIC;
    private ArrayList<OrderObject> orderObjects = new ArrayList<>();
    private String channelid = "my other channelid";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        final RecyclerView recyclerView;
        recyclerView = view.findViewById(R.id.ordersrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        final OrdersAdapter adapter = new OrdersAdapter(orderObjects, getActivity());
        DatabaseReference orderreference = FirebaseDatabase.getInstance().getReference("/orders");
        final String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        Query query = orderreference.orderByChild("farmname").equalTo(farm_name);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.exists()) {
                    // createnotification();
                    OrderObject orderObject = dataSnapshot.getValue(OrderObject.class);
                    assert orderObject != null;
                    String orderamount = orderObject.getOrderamount();
                    String shopname = orderObject.getShopname();
                    String shoplocation = orderObject.getShoplocation();
                    String timestamp = orderObject.getTimestamp();
                    String productname = orderObject.getProductname();
                    String quantity = orderObject.getQuantity();
                    OrderObject orderObject1 = new OrderObject(productname, orderamount, shopname, quantity, shoplocation, timestamp);
                    orderObjects.add(orderObject1);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);


                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                createnotification();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }

    private void createnotification() {
        int notificationid = 1;
        Intent intent = new Intent(getActivity(), ProductsActivityMain.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Objects.requireNonNull(getActivity()), channelid)
                .setSmallIcon(R.drawable.ic_shopping_basket)
                .setContentTitle("A new order Has arrived")
                .setContentText("You will be contacted for Confirmation ASAP, please wait...")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Click to Open Please"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent);
        createNotificationChannel();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(notificationid, builder.build());


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "mychanneltwo";
            String description = "it is a notifications channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelid, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = Objects.requireNonNull(getActivity()).getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }



    /*Enable options menu in this fragment*/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.menus, menu);
        super.onCreateOptionsMenu(menu, inflater);
        //hide item (sort)
        menu.findItem(R.id.newproduct).setVisible(false);
        menu.findItem(R.id.expenses).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();
/*

        if (id == R.id.search) {

            Intent intent = new Intent(getActivity(), AddProduct.class);
            startActivity(intent);

        }
*/


        return super.onOptionsItemSelected(item);
    }


}
