package com.tungngt.dev.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tungngt.dev.databinding.ActivityServerBinding;

public class ServerActivity extends AppCompatActivity {

    private ActivityServerBinding activityServerBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityServerBinding = ActivityServerBinding.inflate(getLayoutInflater());
        setContentView(activityServerBinding.getRoot());

    }
}
