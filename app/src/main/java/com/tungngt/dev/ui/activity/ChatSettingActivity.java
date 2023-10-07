package com.tungngt.dev.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayoutMediator;
import com.tungngt.dev.MyApplication;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.databinding.ActivityChatSettingBinding;
import com.tungngt.dev.ui.adapter.ChatSettingPagerAdapter;
import com.tungngt.dev.viewmodel.ChatSettingViewModel;

public class ChatSettingActivity extends AppCompatActivity {

    private ActivityChatSettingBinding activityChatSettingBinding;
    private AppContainer appContainer;
    private ChatSettingViewModel chatSettingViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChatSettingBinding = ActivityChatSettingBinding.inflate(getLayoutInflater());
        setContentView(activityChatSettingBinding.getRoot());

        appContainer = ((MyApplication) getApplication()).appContainer;
        chatSettingViewModel = new ViewModelProvider(this, appContainer.getChatSettingViewModelFactory())
                .get(ChatSettingViewModel.class);

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

    public AppContainer getAppContainer() {
        return appContainer;
    }

    public ChatSettingViewModel getChatSettingViewModel() {
        return chatSettingViewModel;
    }
}
