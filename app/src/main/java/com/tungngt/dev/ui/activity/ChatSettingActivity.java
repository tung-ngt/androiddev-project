package com.tungngt.dev.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.tungngt.dev.databinding.ActivityChatSettingBinding;
import com.tungngt.dev.ui.adapter.ChatSettingPagerAdapter;

public class ChatSettingActivity extends AppCompatActivity {

    private ActivityChatSettingBinding activityChatSettingBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatSettingBinding = ActivityChatSettingBinding.inflate(getLayoutInflater());
        setContentView(activityChatSettingBinding.getRoot());

        activityChatSettingBinding.pager.setAdapter(new ChatSettingPagerAdapter(this));

        new TabLayoutMediator(
                activityChatSettingBinding.tabLayout,
                activityChatSettingBinding.pager,
                (tab, position) -> {
                    if (position == 0)
                        tab.setText("People");
                    if (position == 1)
                        tab.setText("Information");
                }
        ).attach();


        activityChatSettingBinding.topAppBar.setNavigationOnClickListener(v -> {
            finish();
        });

    }
}
