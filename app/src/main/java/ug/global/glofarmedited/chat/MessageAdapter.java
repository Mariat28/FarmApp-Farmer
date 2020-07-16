package ug.global.glofarmedited.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.Holder> {
    private Context context;
    private ArrayList<Message> messages;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.message.setText(messages.get(position).getMessage());
        holder.sender.setText(messages.get(position).getSender());
        holder.time.setText(messages.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView time, message, sender;

        public Holder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            message = itemView.findViewById(R.id.message);
            sender = itemView.findViewById(R.id.sender);
        }
    }
}
