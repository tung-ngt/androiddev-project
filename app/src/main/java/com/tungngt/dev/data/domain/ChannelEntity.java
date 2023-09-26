package com.tungngt.dev.data.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "channel_entity")
public class ChannelEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    private String handle;
    private String name;
    private Integer color;

    private String description;

    public ChannelEntity(String handle, String name, Integer color) {
        this.handle = handle;
        this.name = name;
        this.color = color;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
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
                ", handle='" + handle + '\'' +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
