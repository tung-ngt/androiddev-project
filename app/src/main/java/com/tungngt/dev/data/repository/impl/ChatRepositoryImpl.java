package com.tungngt.dev.data.repository.impl;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.data.repository.ChatRepository;
import com.tungngt.dev.database.dao.ChatDao;
import com.tungngt.dev.database.dao.UserDao;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ChatEntity;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.model.Message;
import com.tungngt.dev.network.service.IRCService;

import java.util.List;

public class ChatRepositoryImpl implements ChatRepository {
    private IRCService ircService;
    private ChatDao chatDao;
    private UserDao userDao;

    public ChatRepositoryImpl(IRCService ircService, ChatDao chatDao, UserDao userDao) {
        this.ircService = ircService;
        this.chatDao = chatDao;
        this.userDao = userDao;
    }

    @Override
    public LiveData<List<Message>> loadChatFromChannel(Long channelId) {
        return chatDao.getMessageInChannel(channelId);
    }

    @Override
    public void sendMessage(ChatEntity chat, ChannelEntity channel) {
        chatDao.insertAll(chat);
        ircService.sendMessage(chat.getMessage(), channel.getHandle());
    }

    @Override
    public void joinChannel(ChannelEntity channel) {
        ircService.joinChannel(channel.getHandle());
    }

    @Override
    public void listenForMessage(ChannelEntity channel, String listenerType) {
        ircService.addOnReceivedMessageListener(
                channel.getHandle(),
                listenerType,
                (sender, receiver, message, time) -> {
                    UserEntity user = userDao
                            .getUserFromServerWithUsername(channel.getServerId(), sender);
                    Long userId;
                    if (user == null) {
                        userId = userDao.insertAll(new UserEntity(
                                channel.getServerId(),
                                sender,
                                "",
                                "",
                                sender,
                                0xFF000000
                        )).get(0);
                    } else {
                        userId = user.getId();
                    }
                    chatDao.insertAll(new ChatEntity(
                            channel.getId(),
                            userId,
                            message,
                            time
                    ));
                }
        );
    }

    @Override
    public void unListenForMessage(ChannelEntity channel, String listenerType) {
        ircService.removeOnReceivedMessageListener(channel.getHandle(), listenerType);
    }
}
