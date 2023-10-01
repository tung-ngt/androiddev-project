package com.tungngt.dev.data.repository.impl;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.data.repository.SearchRepository;
import com.tungngt.dev.database.dao.ChannelDao;
import com.tungngt.dev.database.dao.ServerDao;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.network.service.IRCService;

import java.util.List;

public class SearchRepositoryImpl implements SearchRepository {
    private ServerDao serverDao;
    private IRCService ircService;
    private ChannelDao channelDao;

    public SearchRepositoryImpl(IRCService ircService, ServerDao serverDao, ChannelDao channelDao) {
        this.ircService = ircService;
        this.serverDao = serverDao;
        this.channelDao = channelDao;
    }

    @Override
    public LiveData<List<ServerEntity>> loadServers() {
        return serverDao.getAll();
    }

    @Override
    public List<ServerEntity> getServersNow() {
        return serverDao.getNow();
    }

    @Override
    public void connectToServer(ServerEntity server) {
        ircService.connectServer(server.getUrl(), server.getPort());
    }

    @Override
    public LiveData<List<ChannelEntity>> loadChannels(Long serverId) {
        return channelDao.getSavedChannelFromServer(serverId);
    }

    @Override
    public List<ChannelEntity> getChannelsNow(Long serverId) {
        return channelDao.getSavedChannelFromServerNow(serverId);
    }
}
