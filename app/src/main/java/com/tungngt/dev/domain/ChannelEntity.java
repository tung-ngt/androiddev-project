package com.tungngt.dev.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "channel_entity")
public class ChannelEntity implements Comparable<ChannelEntity>, Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    @ColumnInfo(name = "server_id")
    private Long serverId;
    private String handle;
    private String name;
    private Integer color;

    private String description;

    public ChannelEntity(
            Long serverId,
            String handle,
            String name,
            Integer color,
            String description
    ) {
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

    public String getName() { return name; }

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

    @Override
    public int compareTo(ChannelEntity o) {
        int compareHandle = this.handle.compareTo(o.handle);
        if (compareHandle == 0) {
            return this.name.compareTo(o.name);
        }
        return compareHandle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelEntity that = (ChannelEntity) o;
        return id.equals(that.id)
                && Objects.equals(serverId, that.serverId)
                && Objects.equals(handle, that.handle)
                && Objects.equals(name, that.name)
                && Objects.equals(color, that.color)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serverId, handle, name, color, description);
    }

    public String getNameAndHandle() {
        return name + " (" + handle + ")";
    }
}
