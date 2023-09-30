package com.tungngt.dev.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.data.repository.ChatRepository;
import com.tungngt.dev.viewmodel.ChatViewModel;

public class ChatViewModelFactory implements ViewModelProvider.Factory {
    private final ChatRepository chatRepository;

    public ChatViewModelFactory(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ChatViewModel(chatRepository);
    }
}
