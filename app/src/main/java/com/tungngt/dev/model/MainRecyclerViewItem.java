package com.tungngt.dev.model;

import com.tungngt.dev.domain.ChannelEntity;

public abstract class MainRecyclerViewItem {
    public static int CHANNEL = 0;
    public static int SEARCH_BAR = 1;
    public static int ACTIVE_USER_BAR = 2;

    public abstract int getViewType();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MainRecyclerViewItem)) return false;
        return sameType((MainRecyclerViewItem) o);
    }

    public boolean sameType(MainRecyclerViewItem o) {
        return this.getViewType() == o.getViewType();
    }

    public static class SearchBar extends MainRecyclerViewItem {
        @Override
        public int getViewType() {
            return SEARCH_BAR;
        }
    }

    public static class ActiveUserBar extends MainRecyclerViewItem {
        @Override
        public int getViewType() {
            return ACTIVE_USER_BAR;
        }
    }

    public static class Channel extends MainRecyclerViewItem {
        public ChannelEntity channelEntity;
        public Channel(ChannelEntity channelEntity) {
            this.channelEntity = channelEntity;
        }
        @Override
        public int getViewType() {
            return CHANNEL;
        }
        @Override
        public boolean equals(Object o) {
            boolean sameType = super.equals(o);
            if (!sameType) return false;
            return channelEntity.equals(((Channel) o).channelEntity);
        }
    }

}
