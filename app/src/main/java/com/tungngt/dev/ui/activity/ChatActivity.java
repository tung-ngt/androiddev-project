package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActivityChatBinding;
import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.model.Message;
import com.tungngt.dev.service.IRCService;
import com.tungngt.dev.service.impl.IRCServiceImpl;
import com.tungngt.dev.ui.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity{
    private static final String TAG = "ChatActivity";
    private EditText chatTxt;
    private Button sendButton;
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
        else {
            activityChatBinding.setChannel(new ChannelItem("USTH", "usth.edu.vn", "123", "123", "123", "123", 0xFF78281F));
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

        activityChatBinding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.editChanel) {
                Intent intent = new Intent(this, ChatSettingActivity.class);
                startActivity(intent);
            }
            return true;
        });

        IRCService ircService = IRCServiceImpl.getInstance();

        ircService.connectServer("chat.freenode.net", 6669);
        ircService.login("tungnguyen123", "Tung");
        ircService.joinChannel("#usth");
        ircService.sendMessage("test message", "#usth");

//        ircService.setOnReceivedMessageListener((sender, receiver, message) -> {
//            messages.add(new Message(sender, message, "12:00", "1"));
//            chatAdapter.differ.submitList(messages);
//            Log.i(TAG, "onCreate: " + sender + " " + receiver + " " + message);
//        });
    }
}
