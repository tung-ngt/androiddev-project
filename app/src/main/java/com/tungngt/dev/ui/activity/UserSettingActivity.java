package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tungngt.dev.databinding.ActivityUserSettingsBinding;

public class UserSettingActivity extends AppCompatActivity {
    ActivityUserSettingsBinding activityUserSettingsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserSettingsBinding = ActivityUserSettingsBinding.inflate(getLayoutInflater());
        setContentView(activityUserSettingsBinding.getRoot());
    }
}