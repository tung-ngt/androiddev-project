package com.tungngt.dev.data.repository;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.model.MainRecyclerViewItem;

import java.util.List;

public interface MainRepository {
    LiveData<List<MainRecyclerViewItem.Channel>> loadSavedRcChannels(Long serverId);
    void addChannel(Long serverId, String handle, String name, Integer color, String description);
    void deleteChannel(ChannelEntity channelEntity);
    void restoreChannel(ChannelEntity channelEntity);
}
