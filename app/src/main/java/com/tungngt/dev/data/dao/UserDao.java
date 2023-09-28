package com.tungngt.dev.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.tungngt.dev.data.domain.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user_entity")
    LiveData<List<UserEntity>> getAll();

    @Insert
    void insertAll(UserEntity... users);

    @Query("DELETE FROM user_entity")
    void clearTable();
}
