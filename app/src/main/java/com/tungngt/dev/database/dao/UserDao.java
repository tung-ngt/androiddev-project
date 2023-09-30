package com.tungngt.dev.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.tungngt.dev.domain.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_entity")
    LiveData<List<UserEntity>> getAll();

    @Query("SELECT * FROM user_entity WHERE server_id = :serverId AND username = :usernameParam")
    UserEntity getUserFromServerWithUsername(Long serverId, String usernameParam);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(UserEntity... users);

    @Query("DELETE FROM user_entity")
    void clearTable();

    @Update
    void update(UserEntity user);

    @Delete
    void delete(UserEntity user);

    @Query("SELECT * FROM user_entity WHERE server_id = :serverId")
    LiveData<List<UserEntity>> getAllUserFromServer(Long serverId);

}
