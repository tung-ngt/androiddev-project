package com.tungngt.dev.data.repository;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.model.MainRecyclerViewItem;

import java.util.List;

public interface MainRepository {
    LiveData<List<MainRecyclerViewItem.Channel>> loadSavedRcChannels(Long serverId);
}
