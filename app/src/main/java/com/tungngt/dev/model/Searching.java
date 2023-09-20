package com.tungngt.dev.model;

import androidx.annotation.Nullable;

import java.util.Date;
import java.util.Objects;

public class Searching implements Comparable<Searching> {
    public String id;
    public String chnName;
    public String imageUrl;
    public Searching(String id, String chnName, String imageUrl) {
        this.id = id;
        this.chnName = chnName;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Searching that = (Searching) o;
        return Objects.equals(chnName, that.chnName);
    }

    public int compareTo(Searching o) {
        // TODO: implement compare to
        return 0;
    }
}