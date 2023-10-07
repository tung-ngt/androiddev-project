package com.tungngt.dev.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.data.repository.ChatSettingRepository;
import com.tungngt.dev.viewmodel.ChatSettingViewModel;

public class ChatSettingViewModelFactory implements ViewModelProvider.Factory {
    private final ChatSettingRepository chatSettingRepository;
    public ChatSettingViewModelFactory(ChatSettingRepository chatSettingRepository) {
        this.chatSettingRepository = chatSettingRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChatSettingViewModel(chatSettingRepository);
    }
}
