package com.tungngt.dev.model;

import java.util.Objects;

public class ActiveUser implements Comparable<ActiveUser> {
    public String username;
    public String imageUrl;
    public Integer color;

    public ActiveUser(String username, String imageUrl, Integer color) {
        this.username = username;
        this.imageUrl = imageUrl;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveUser that = (ActiveUser) o;
        return Objects.equals(username, that.username);
    }
    @Override
    public int compareTo(ActiveUser o) {
        if (this.username.compareTo(o.username) == 0) {
            return this.imageUrl.compareTo(o.imageUrl);
        }

        return this.username.compareTo(o.username);
    }
}
