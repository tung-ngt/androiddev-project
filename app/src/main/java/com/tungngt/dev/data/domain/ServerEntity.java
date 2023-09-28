package com.tungngt.dev.data.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "server_entity")
public class ServerEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    private String url;
    private Integer port;

    private String name;
    private Integer color;

    public ServerEntity(String url, Integer port, String name, Integer color) {
        this.url = url;
        this.port = port;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
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

    @Override
    public String toString() {
        return "ServerEntity{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", port=" + port +
                ", name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
