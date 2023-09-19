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
public class ChatActivity extends AppCompatActivity{
    private static final String TAG = "ChatActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        Log.d(TAG, "onCreate: started");
        MaterialToolbar navIcon = findViewById(R.id.topAppBar);
        navIcon.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked navIcon.");
                Intent intent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
