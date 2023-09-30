package com.tungngt.dev.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tungngt.dev.domain.ServerEntity;

import java.util.List;

@Dao
public interface ServerDao {
    @Query("SELECT * FROM server_entity")
    LiveData<List<ServerEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(ServerEntity... servers);

    @Query("DELETE FROM server_entity")
    void clearTable();

    @Delete
    void delete(ServerEntity server);

    @Update
    void update(ServerEntity server);
}
