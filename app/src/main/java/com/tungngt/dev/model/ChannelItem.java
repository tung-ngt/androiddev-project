package com.tungngt.dev.model;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

public class ChannelItem implements Comparable<ChannelItem> {
    public String name;
    public String imageUrl;
    public String latestChatSender;
    public String latestChat;
    public String latestChatTime;

    public ChannelItem(String name, String imageUrl, String latestChatSender, String latestChat, String latestChatTime) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.latestChatSender = latestChatSender;
        this.latestChat = latestChat;
        this.latestChatTime = latestChatTime;
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
}