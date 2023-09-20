package com.tungngt.dev.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tungngt.dev.databinding.FragmentUserSettingsBinding;

public class UserSettingActivity extends AppCompatActivity {
    FragmentUserSettingsBinding fragmentUserSettingsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentUserSettingsBinding = FragmentUserSettingsBinding.inflate(getLayoutInflater());
        setContentView(fragmentUserSettingsBinding.getRoot());
    }
}