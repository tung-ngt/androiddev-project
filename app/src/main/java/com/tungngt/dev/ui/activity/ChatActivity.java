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
                ChatActivity.this.finish();
            }
        );
    }
}