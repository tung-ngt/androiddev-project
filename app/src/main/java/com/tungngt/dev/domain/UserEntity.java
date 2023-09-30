package com.tungngt.dev.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "user_entity")
public class UserEntity implements Comparable<UserEntity>, Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    @ColumnInfo(name = "server_id")
    private Long serverId;
    private String username;
    private String password;
    @ColumnInfo(name = "real_name")
    private String realName;
    private String nickname;
    private Integer color;

    public UserEntity(
            Long serverId,
            String username,
            String password,
            String realName,
            String nickname,
            Integer color
    ) {
        this.serverId = serverId;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.nickname = nickname;
        this.color = color;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", serverId=" + serverId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", color=" + color +
                '}';
    }

    @Override
    public int compareTo(UserEntity o) {
        int compareUsername = this.username.compareTo(o.username);
        if (compareUsername == 0) {
            return this.nickname.compareTo(o.nickname);
        }
        return compareUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id.equals(that.id)
                && Objects.equals(serverId, that.serverId)
                && Objects.equals(username, that.username)
                && Objects.equals(password, that.password)
                && Objects.equals(realName, that.realName)
                && Objects.equals(nickname, that.nickname)
                && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, serverId, username, password, realName, nickname, color);
    }
}
