package com.tungngt.dev.model;
import androidx.room.ColumnInfo;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Message implements Comparable<Message>{
    public Long messageId;
    public String username;
    public String message;

    @ColumnInfo(name = "channel_id")
    public Long channelId;
    @ColumnInfo(name = "send_time")
    public Date sendTime;
    @ColumnInfo(name = "color")
    public Integer userColor;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(messageId, message1.messageId)
                && Objects.equals(username, message1.username)
                && Objects.equals(message, message1.message)
                && Objects.equals(channelId, message1.channelId)
                && Objects.equals(sendTime, message1.sendTime)
                && Objects.equals(userColor, message1.userColor);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                ", channelId=" + channelId +
                ", sendTime=" + sendTime +
                ", userColor=" + userColor +
                '}';
    }

    @Override
    public int compareTo(Message o) {
        if (this.sendTime.compareTo(o.sendTime) == 0) {
            return this.username.compareTo(o.username);
        }

        return this.sendTime.compareTo(o.sendTime);
    }

    public String getSendTimeRepresentation() {
        if (sendTime == null) return "...";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sendTime);
        int month = calendar.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return String.format("%02dh%02d (%02d/%02d)", hour, minute, month, day);
    }
}
