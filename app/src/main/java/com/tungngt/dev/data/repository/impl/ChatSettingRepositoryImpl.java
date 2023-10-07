package com.tungngt.dev.data.repository.impl;

import com.tungngt.dev.data.repository.ChatSettingRepository;
import com.tungngt.dev.network.service.IRCService;

public class ChatSettingRepositoryImpl implements ChatSettingRepository {
    private final IRCService ircService;

    public ChatSettingRepositoryImpl(IRCService ircService) {
        this.ircService = ircService;
    }

    @Override
    public void requestActiveUsers(String channelHandle, IRCService.OnActiveUsersReceivedListener onActiveUsersReceivedListener) {
        ircService.requestActiveUsers(channelHandle, onActiveUsersReceivedListener);
    }
}
