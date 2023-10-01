package com.tungngt.dev.data.repository;

import com.tungngt.dev.network.service.IRCService;

public interface ChatSettingRepository {
    void requestActiveUsers(String channelHandle, IRCService.OnActiveUsersReceivedListener onActiveUsersReceivedListener);
}
