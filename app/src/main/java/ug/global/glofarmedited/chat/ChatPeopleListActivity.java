package ug.global.glofarmedited.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ug.global.glofarmedited.R;

public class ChatPeopleListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_people_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ArrayList<ChatPerson> chatPeople = new ArrayList<>();
        ChatPersonListAdapter chatPersonListAdapter = new ChatPersonListAdapter(this, chatPeople);
        for (int i = 0; i < 5; i++) {
            chatPeople.add(new ChatPerson("dksjdf","Name","Farm","khsbdjhdf"));
            chatPersonListAdapter.notifyDataSetChanged();
        }
        recyclerView.setAdapter(chatPersonListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}