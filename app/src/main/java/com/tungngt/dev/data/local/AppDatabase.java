package com.tungngt.dev.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tungngt.dev.data.dao.ChannelDao;
import com.tungngt.dev.data.domain.ChannelEntity;

@Database(entities = {ChannelEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ChannelDao getChannelDao();
}
