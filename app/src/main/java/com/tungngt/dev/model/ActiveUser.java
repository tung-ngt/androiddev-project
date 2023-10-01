package com.tungngt.dev.model;

import android.graphics.Color;

import java.util.Objects;
import java.util.Random;

public class ActiveUser implements Comparable<ActiveUser> {
    public String username;
    public Integer color;

    public ActiveUser(String username) {
        this.username = username;
        Random rnd = new Random();
        this.color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
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
        return this.username.compareTo(o.username);
    }
}
