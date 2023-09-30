package com.tungngt.dev.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tungngt.dev.domain.ChatEntity;
import com.tungngt.dev.model.Message;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat_entity")
    LiveData<List<ChatEntity>> getAll();

    @Query("SELECT " +
            "chat_entity.id AS messageId, " +
            "message, " +
            "username, " +
            "channel_id," +
            "send_time," +
            "color " +
            "FROM chat_entity " +
            "INNER JOIN user_entity ON sender_id = user_entity.id " +
            "WHERE channel_id = :channelId")
    LiveData<List<Message>> getMessageInChannel(Long channelId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(ChatEntity... chatEntities);

    @Query("DELETE FROM chat_entity")
    void clearTable();

    @Query("DELETE FROM chat_entity WHERE channel_id = :channelId")
    void clearChatFromChannel(Long channelId);

}
