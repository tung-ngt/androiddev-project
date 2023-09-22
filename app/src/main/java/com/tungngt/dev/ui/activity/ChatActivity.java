package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;


import android.os.Bundle;
import android.util.Log;

import com.tungngt.dev.databinding.ActivityChatBinding;
import com.tungngt.dev.model.ChannelItem;

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
    }
}
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Tung", "hello", "12:00", "1"));
        messages.add(new Message("Tung", "how are you", "12:00", "1"));
        messages.add(new Message("Tung", "hi", "12:00", "1"));
        messages.add(new Message("Tung", "Feeder XD", "12:00", "1"));
        ChatAdapter chatAdapter = new ChatAdapter();
        chatAdapter.differ.submitList(messages);
        chatLayoutBinding.chatRecyclerView.setAdapter(chatAdapter);

        MaterialToolbar navIcon = findViewById(R.id.topAppBar);
        navIcon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
