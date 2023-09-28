package com.tungngt.dev.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tungngt.dev.data.domain.ChannelEntity;

import java.util.List;

@Dao
public interface ChannelDao {
    @Query("SELECT * FROM channel_entity")
    LiveData<List<ChannelEntity>> getAll();

    @Insert
    void insertAll(ChannelEntity... channels);
}
