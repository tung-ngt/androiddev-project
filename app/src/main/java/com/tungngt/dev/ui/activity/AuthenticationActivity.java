package com.tungngt.dev.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tungngt.dev.databinding.ActivityAuthenticationBinding;
import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.model.Server;

public class AuthenticationActivity extends AppCompatActivity {

    private ActivityAuthenticationBinding activityAuthenticationBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAuthenticationBinding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(activityAuthenticationBinding.getRoot());

        // Process intent when clicked on a server
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Server server = (Server) extras.getSerializable("server");

            if (server != null) {

                activityAuthenticationBinding.setServer(server);

            }
        }

    }
}
