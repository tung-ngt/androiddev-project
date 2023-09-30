package com.tungngt.dev.data.repository.impl;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.tungngt.dev.data.repository.MainRepository;
import com.tungngt.dev.database.dao.ChannelDao;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.model.MainRecyclerViewItem;
import com.tungngt.dev.network.service.IRCService;

import java.util.List;
import java.util.stream.Collectors;

public class MainRepositoryImpl implements MainRepository {
    private IRCService ircService;
    private ChannelDao channelDao;

    public MainRepositoryImpl(IRCService ircService, ChannelDao channelDao) {
        this.ircService = ircService;
        this.channelDao = channelDao;
    }

    @Override
    public LiveData<List<MainRecyclerViewItem.Channel>> loadSavedRcChannels(Long serverId) {
        return Transformations.map(
                channelDao.getSavedChannelFromServer(serverId),
                channelEntities ->
                        channelEntities.stream()
                                .map(channel -> new MainRecyclerViewItem.Channel(channel))
                                .collect(Collectors.toList())
        );
    }

    @Override
    public void addChannel(Long serverId, String handle, String name, Integer color, String description) {
        channelDao.insertAll(new ChannelEntity(
                serverId,
                handle,
                name,
                color,
                description
        ));
    }
}
