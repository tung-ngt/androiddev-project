package com.tungngt.dev.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tungngt.dev.data.domain.ServerEntity;

import java.util.List;

@Dao
public interface ServerDao {
    @Query("SELECT * FROM server_entity")
    LiveData<List<ServerEntity>> getAll();

    @Insert
    void insertAll(ServerEntity... servers);
}
