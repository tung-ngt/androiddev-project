package com.tungngt.dev.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.data.repository.ChatRepository;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ChatEntity;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.model.Message;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private final ChatRepository chatRepository;

    public ChatViewModel(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }
    public LiveData<List<Message>> getMessages(ChannelEntity channel) {
        return chatRepository.loadChatFromChannel(channel.getId());
    }

    public void sendMessage(
            String message,
            ChannelEntity channel,
            UserEntity loggedInUser
    ) {
        new Thread(() -> {
            chatRepository.sendMessage(
                    new ChatEntity(
                            channel.getId(),
                            loggedInUser.getId(),
                            message,
                            Calendar.getInstance().getTime()
                    ),
                    channel
            );
        }).start();
    }

    public void joinChannel(ChannelEntity channel) {
        chatRepository.joinChannel(channel);
    }

    public void listenForMessage(ChannelEntity channel) {
        chatRepository.listenForMessage(channel, "ChatUI");
    }

    public void unListenForMessage(ChannelEntity channel) {
        chatRepository.unListenForMessage(channel, "ChatUI");
    }
}
