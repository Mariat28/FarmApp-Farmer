package ug.global.glofarmedited.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ug.global.glofarmedited.R;

public class ChatPersonListAdapter extends RecyclerView.Adapter<ChatPersonListAdapter.Holder> {
    Context context;
    private ArrayList<ChatPerson> chatPeople;

    public ChatPersonListAdapter(Context context, ArrayList<ChatPerson> chatPeople) {
        this.context = context;
        this.chatPeople = chatPeople;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.person_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final ChatPerson chatPerson = chatPeople.get(position);
        Glide.with(context).load(chatPerson.getPhoto()).into(holder.avatar);
        holder.name.setText(chatPerson.getName());
        holder.farm.setText(chatPerson.getFarmname());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
                context.startActivity(new Intent(context, ChatDetailActivity.class).putExtra("key", chatPerson.getKey()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatPeople.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        CircleImageView avatar;
        TextView name, farm;
        CardView cardView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.message);
            farm = itemView.findViewById(R.id.sender);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
