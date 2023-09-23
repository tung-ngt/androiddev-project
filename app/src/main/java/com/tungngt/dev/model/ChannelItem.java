package com.tungngt.dev.model;

import java.io.Serializable;
import java.util.Objects;

public class ChannelItem implements Comparable<ChannelItem>, Serializable {
    public String id;
    public String name;
    public String imageUrl;
    public String latestChatSender;
    public String latestChat;
    public String latestChatTime;

    public Integer color;

    public int type;

    public static int CHANNEL = 0;
    public static int SEARCH_BAR = 1;
    public static int ACTIVE_USER_BAR = 2;

    public ChannelItem(String id, String name,
                       String imageUrl, String latestChatSender,
                       String latestChat, String latestChatTime,
                       Integer color
    ) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.latestChatSender = latestChatSender;
        this.latestChat = latestChat;
        this.latestChatTime = latestChatTime;
        this.type = CHANNEL;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChannelItem that = (ChannelItem) o;
        return Objects.equals(name, that.name);
    }

    public int compareTo(ChannelItem o) {
        // TODO: implement compare to
        return 0;
    }

    public String getChannelId() {
        return id;
    }

    public String getUserName() {
        return id;
    }

    public String getChannelName() {
        return name;
    }
}