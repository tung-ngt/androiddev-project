package com.tungngt.dev.database;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.tungngt.dev.database.converters.Converters;
import com.tungngt.dev.database.dao.ChannelDao;
import com.tungngt.dev.database.dao.ChatDao;
import com.tungngt.dev.database.dao.ServerDao;
import com.tungngt.dev.database.dao.UserDao;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ChatEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.domain.UserEntity;

@Database(
        entities = {
                ChannelEntity.class,
                ChatEntity.class,
                ServerEntity.class,
                UserEntity.class
        },
        version = 2
//        autoMigrations = {
//                @AutoMigration(from = 1, to = 2)
//        }
)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ChannelDao getChannelDao();
    public abstract ChatDao getChatDao();
    public abstract ServerDao getServerDao();
    public abstract UserDao getUserDao();
}
