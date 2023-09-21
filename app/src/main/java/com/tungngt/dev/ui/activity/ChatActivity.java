package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Context;


import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ChatLayoutBinding;

import com.tungngt.dev.model.Message;
import com.tungngt.dev.ui.adapter.ChatAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity{
    private ChatLayoutBinding chatLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatLayoutBinding = ChatLayoutBinding.inflate(getLayoutInflater());
        setContentView(chatLayoutBinding.getRoot());
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
