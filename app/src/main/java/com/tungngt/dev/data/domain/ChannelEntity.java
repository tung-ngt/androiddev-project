package com.tungngt.dev.data.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "channel_entity")
public class ChannelEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    @ColumnInfo(name = "server_id")
    private Long serverId;
    private String handle;
    private String name;
    private Integer color;

    private String description;

    public ChannelEntity(Long serverId, String handle, String name, Integer color, String description) {
        this.serverId = serverId;
        this.handle = handle;
        this.name = name;
        this.color = color;
        this.description = description;

    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ChannelEntity{" +
                "id=" + id +
                ", serverId=" + serverId +
                ", handle='" + handle + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", description='" + description + '\'' +
                '}';
    }
}
