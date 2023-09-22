package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;


import android.os.Bundle;
import android.util.Log;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActivityChatBinding;
import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.model.Message;
import com.tungngt.dev.ui.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity{
    private static final String TAG = "ChatActivity";

    private ActivityChatBinding activityChatBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(activityChatBinding.getRoot());


        // Process intent when clicked on a channel
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ChannelItem channelItem = (ChannelItem) extras.getSerializable("channelItem");

            if (channelItem != null) {

                activityChatBinding.setChannel(channelItem);

            }
        }


        activityChatBinding.topAppBar.setNavigationOnClickListener((view) -> {
                onBackPressed();
            }
        );

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Tung", "hello", "12:00", "1"));
        messages.add(new Message("Tung", "how are you", "12:00", "1"));
        messages.add(new Message("Tung", "hi", "12:00", "1"));
        messages.add(new Message("Tung", "Feeder XD", "12:00", "1"));
        ChatAdapter chatAdapter = new ChatAdapter();
        chatAdapter.differ.submitList(messages);
        activityChatBinding.chatRecyclerView.setAdapter(chatAdapter);
    }
}
