package com.tungngt.dev.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "server_entity")
public class ServerEntity implements Comparable<ServerEntity>, Serializable {
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

    public String getAddress() {
        return url + " (" + port + ") ";
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

    @Override
    public int compareTo(ServerEntity o) {
        int compareUrl = this.url.compareTo(o.url);
        if (compareUrl == 0) {
            return this.name.compareTo(o.name);
        }
        return compareUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerEntity that = (ServerEntity) o;
        return id.equals(that.id)
                && Objects.equals(url, that.url)
                && Objects.equals(port, that.port)
                && Objects.equals(name, that.name)
                && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, port, name, color);
    }
}
