package ug.global.glofarmedited.chat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import ug.global.glofarmedited.R;

public class ChatDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle("Chat Detail");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        ArrayList<Message> messages=new ArrayList<>();
        MessageAdapter messageAdapter=new MessageAdapter(this,messages);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        for (int i = 0; i < 12; i++) {
            messages.add(new Message("12:23","sdkjnkshdbfkhsdfsdf sfsefrwerwefuhew7f 8ergfo8erf","Jon","me"));
            messageAdapter.notifyDataSetChanged();
        }
    }
}