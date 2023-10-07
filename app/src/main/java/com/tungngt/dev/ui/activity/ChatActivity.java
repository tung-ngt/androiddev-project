package com.tungngt.dev.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.MyApplication;
import com.tungngt.dev.R;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.databinding.ActivityChatBinding;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.network.service.IRCService;
import com.tungngt.dev.network.service.impl.IRCServiceImpl;
import com.tungngt.dev.ui.adapter.ChatAdapter;
import com.tungngt.dev.viewmodel.ChatViewModel;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity{
    private static final String TAG = "ChatActivity";
    private ActivityChatBinding activityChatBinding;
    private ChatViewModel chatViewModel;
    private AppContainer appContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatBinding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(activityChatBinding.getRoot());

        appContainer = ((MyApplication) getApplication()).appContainer;

        chatViewModel = new ViewModelProvider(this,
                appContainer.getChatViewModelFactory()
        ).get(ChatViewModel.class);




        // Process intent when clicked on a channel
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ChannelEntity channel = (ChannelEntity) extras.getSerializable("channel");

            if (channel != null) {
                activityChatBinding.setChannel(channel);
                appContainer.setCurrentChannel(channel);
            }
        }
        else {
           // TODO: implement back if not have intent
        }

        // Setup navigation press
        activityChatBinding.topAppBar.setNavigationOnClickListener((view) -> {onBackPressed();});

        // Setup adapter
        ChatAdapter chatAdapter = new ChatAdapter();
        chatViewModel
                .getMessages(appContainer.getCurrentChannel())
                .observe(this, chatlist -> {
                    chatAdapter.differ.submitList(new ArrayList<>(chatlist));
                    activityChatBinding.chatRecyclerView
                            .smoothScrollToPosition(chatAdapter.getItemCount());
                });
        activityChatBinding.chatRecyclerView.setAdapter(chatAdapter);


        activityChatBinding.topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.editChanel) {
                Intent intent = new Intent(this, ChatSettingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("channel", appContainer.getCurrentChannel());
                intent.putExtras(bundle);
                startActivity(intent, bundle);
            }
            return true;
        });

        chatViewModel.joinChannel(appContainer.getCurrentChannel());
        chatViewModel.listenForMessage(appContainer.getCurrentChannel());

        activityChatBinding.sendButton.setOnClickListener(v -> {
            String message = activityChatBinding.chatTxt.getText().toString();
            chatViewModel.sendMessage(
                    message,
                    appContainer.getCurrentChannel(),
                    appContainer.getLoggedInUser()
            );
            activityChatBinding.chatTxt.setText("");
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        chatViewModel.unListenForMessage(appContainer.getCurrentChannel());
    }
}
