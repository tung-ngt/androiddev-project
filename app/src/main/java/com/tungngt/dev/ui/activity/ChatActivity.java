package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;

import android.os.Bundle;
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
import com.tungngt.dev.ui.viewmodel.ChatViewModel;

import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity{
    private static final String TAG = "ChatActivity";
    private EditText chatTxt;
    private Button sendButton;
    private ActivityChatBinding activityChatBinding;
    private ChatViewModel chatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(activityChatBinding.getRoot());

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);


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

        ChatAdapter chatAdapter = new ChatAdapter();

        chatViewModel.getMessages().observe(this, messageList -> {
            chatAdapter.differ.submitList(new ArrayList<>(messageList));
        });
        activityChatBinding.chatRecyclerView.setAdapter(chatAdapter);

        activityChatBinding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.editChanel) {
                Intent intent = new Intent(this, ChatSettingActivity.class);
                startActivity(intent);
            }
            return true;
        });

        IRCService ircService = IRCServiceImpl.getInstance();
        ircService.connectServer("irc.libera.chat", 6667);

        ircService.login("novete", "Tung");
        ircService.joinChannel("#usth");
        ircService.sendMessage("test message", "#usth");

        ircService.setOnReceivedMessageListener((sender, receiver, message, time) -> {
            chatViewModel.postMessage(new Message(sender, message, time, "1"));
            Log.i(TAG, "onCreate: " + sender + " " + receiver + " " + message);
        });



        activityChatBinding.sendButton.setOnClickListener(v -> {
            String text = activityChatBinding.chatTxt.getText().toString();
            Date date = new Date();
            String time = date.getHours() + ":" + date.getMinutes()+ ":" + date.getSeconds();
            ircService.sendMessage(text, "#usth");
            chatViewModel.addMessage(new Message("You", text, time, "1"));
            activityChatBinding.chatTxt.setText("");
        });


    }
}
