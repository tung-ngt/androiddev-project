package com.tungngt.dev.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tungngt.dev.domain.ChannelEntity;

import java.util.List;

@Dao
public interface ChannelDao {
    @Query("SELECT * FROM channel_entity")
    LiveData<List<ChannelEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(ChannelEntity... channels);

    @Query("DELETE FROM channel_entity")
    void clearTable();

    @Update
    void update(ChannelEntity channel);

    @Query("SELECT * FROM channel_entity WHERE server_id = :serverId")
    LiveData<List<ChannelEntity>> getSavedChannelFromServer(Long serverId);

    @Query("SELECT * FROM channel_entity WHERE server_id = :serverId")
    List<ChannelEntity> getSavedChannelFromServerNow(Long serverId);

    @Delete
    void delete(ChannelEntity channel);

    @Query("DELETE FROM channel_entity WHERE server_id = :serverId")
    void deleteAllFromServer(Long serverId);

    // When user restore a channel, we need to restore it with the same channel id
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void restoreChannel(ChannelEntity channelEntity);
}
