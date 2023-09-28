package com.tungngt.dev.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tungngt.dev.data.domain.ChatEntity;

import java.util.List;

@Dao
public interface ChatDao {
    @Query("SELECT * FROM chat_entity")
    LiveData<List<ChatEntity>> getAll();

    @Insert
    void insertAll(ChatEntity... chatEntities);

    @Query("DELETE FROM chat_entity")
    void clearTable();
}
