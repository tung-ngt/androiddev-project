package com.tungngt.dev.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tungngt.dev.databinding.ActivityAuthenticationBinding;

public class AuthenticationActivity extends AppCompatActivity {

    private ActivityAuthenticationBinding activityAuthenticationBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAuthenticationBinding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(activityAuthenticationBinding.getRoot());

    }
}
