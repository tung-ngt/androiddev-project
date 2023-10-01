package com.tungngt.dev.data.repository;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;

import java.util.List;

public interface SearchRepository {

    LiveData<List<ServerEntity>> loadServers();

    List<ServerEntity> getServersNow();
    void connectToServer(ServerEntity server);

    LiveData<List<ChannelEntity>> loadChannels(Long serverId);

    List<ChannelEntity> getChannelsNow(Long serverid);

}
