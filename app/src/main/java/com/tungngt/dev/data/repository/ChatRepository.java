package com.tungngt.dev.data.repository;


import androidx.lifecycle.LiveData;

import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ChatEntity;
import com.tungngt.dev.model.Message;

import java.util.List;

public interface ChatRepository {
    LiveData<List<Message>> loadChatFromChannel(Long channelId);

    void sendMessage(ChatEntity chat, ChannelEntity channel);

    void joinChannel(ChannelEntity channel);

    void listenForMessage(ChannelEntity channel, String listenerType);
    void unListenForMessage(ChannelEntity channel, String listenerType);
}
