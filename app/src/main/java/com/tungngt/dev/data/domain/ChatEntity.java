package com.tungngt.dev.data.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "chat_entity")
public class ChatEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    @ColumnInfo(name = "channel_id")
    private Long channelId;
    @ColumnInfo(name = "sender_id")
    private Long senderId;

    private String message;
    @ColumnInfo(name = "send_time")
    private Date sendTime;

    public ChatEntity(Long channelId, Long senderId, String message, Date sendTime) {
        this.channelId = channelId;
        this.senderId = senderId;
        this.message = message;
        this.sendTime = sendTime;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", message='" + message + '\'' +
                ", senderId=" + senderId +
                ", sendTime=" + sendTime +
                '}';
    }
}
