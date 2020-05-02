package ug.global.glofarmedited.agromarket.fragments;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
    private ArrayList<OrderObject> orderObjects = new ArrayList<>();
    private String channelid = "my other channelid";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        final ImageView noordersimage = view.findViewById(R.id.noordersimage);
        final TextView noorderstextview = view.findViewById(R.id.noorderstext);
        progressBar = view.findViewById(R.id.orderprogress);
        progressBar.setVisibility(View.VISIBLE);
        sharedPreferences = getActivity().getSharedPreferences("sort settings", MODE_PRIVATE);
        String msorting = sharedPreferences.getString("Sort", "Newest");//if no settings is selected,newest will be default
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        if (msorting.equals("Newest")) {
            linearLayoutManager = new LinearLayoutManager(getActivity());
            //load items from top meaning newest
            linearLayoutManager.setReverseLayout(true);
            linearLayoutManager.setStackFromEnd(true);
        } else if (msorting.equals("Oldest")) {
            linearLayoutManager = new LinearLayoutManager(getActivity());
            //this will load items from bottom meaning oldest first
            linearLayoutManager.setReverseLayout(false);
            linearLayoutManager.setStackFromEnd(false);
        }
        recyclerView = view.findViewById(R.id.ordersrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        final OrdersAdapter adapter = new OrdersAdapter(orderObjects, getActivity());
        DatabaseReference orderreference = FirebaseDatabase.getInstance().getReference("/orders");
        final String farm_name = getActivity().getSharedPreferences(Constants.getSharedPrefs(), MODE_PRIVATE).getString("farm_name", null);
        Query query = orderreference.orderByChild("farmname").equalTo(farm_name);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.exists()) {
                    progressBar.setVisibility(View.INVISIBLE);
                    noordersimage.setVisibility(View.INVISIBLE);
                    noorderstextview.setVisibility(View.INVISIBLE);
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
                    // orderObject.setId(dataSnapshot.getKey());
                    orderObjects.add(orderObject1);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                    createnotification();

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
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {

                    progressBar.setVisibility(View.INVISIBLE);
                    noordersimage.setVisibility(View.VISIBLE);
                    noorderstextview.setVisibility(View.VISIBLE);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


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
                .setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent).
                        setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        createNotificationChannel();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getActivity());
        notificationManagerCompat.notify(notificationid, builder.build());


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "mychanneltwo";
            String description = "it is a notifications channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
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
        menu.findItem(R.id.Profile).setVisible(false);
        menu.findItem(R.id.expenses).setVisible(false);
        menu.findItem(R.id.sortmenu).setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();

        if (id == R.id.sortmenu) {
            //options to display in dialog
            String[] sortOptions = {"Newest", "Oldest"};
            //create alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Sort by").
                    setIcon(R.drawable.ic_sort_black_24dp).
                    setItems(sortOptions, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            //position is the position of the selected item
                            //0 means "Newest" and 1 means "Oldest"
                            if (position == 0) {
                                //sort by newest
                                //Edit shared preferences
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Sort", "Newest");//where sort is the key & newest is the value
                                editor.apply();//save value in our shared preferences
                                Toast.makeText(getActivity(), "Orders sorted by most recent order", Toast.LENGTH_SHORT).show();
                                Objects.requireNonNull(getActivity()).recreate();
                            } else if (position == 1) {
                                //sort by oldest
                                //edit shared prefs
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Sort", "Oldest");
                                editor.apply();
                                Objects.requireNonNull(getActivity()).recreate();
                                Toast.makeText(getActivity(), "Orders sorted by oldest order", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
            builder.show();

        }


        return super.onOptionsItemSelected(item);
    }


}
