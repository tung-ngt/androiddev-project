package com.tungngt.dev.model;
import java.util.Objects;
public class Message implements Comparable<Message>{
    public String sender;
    public String message;
    public String time;
    public String imageUrl;

    public Message(String sender, String message, String time, String imageUrl) {
        this.sender = sender;
        this.message = message;
        this.time = time;
        this.imageUrl = imageUrl;

    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        return Objects.equals(message, that.message);
    }
    @Override
    public int compareTo(Message o) {
        if (this.message.compareTo(o.message) == 0) {
            return this.imageUrl.compareTo(o.imageUrl);
        }

        return this.message.compareTo(o.message);
    }
}
