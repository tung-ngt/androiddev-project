package com.tungngt.dev.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActiveUserBarBinding;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.databinding.SearchBarBinding;
import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.ui.activity.ChatActivity;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {


    public static int SEARCH_BAR = 0;
    public static int ACTIVE_USER_BAR = 1;
    public static int CHANNEL_ITEM = 2;

    public ActiveUserAdapter activeUserAdapter;

    public interface OnSearchBarClicked {
        void click();
    };

    private OnSearchBarClicked onSearchBarClicked;

    public void setOnSearchBarClicked(OnSearchBarClicked onSearchBarClicked) {
        this.onSearchBarClicked = onSearchBarClicked;
    }

    public interface OnActiveUserBarClicked {
        void click();
    };

    private OnActiveUserBarClicked onActiveUserBarClicked;

    public void setOnActiveUserBarClicked(OnActiveUserBarClicked onActiveUserBarClicked) {
        this.onActiveUserBarClicked = onActiveUserBarClicked;
    }

    public ChannelAdapter(ActiveUserAdapter activeUserAdapter, Context context) {
        this.activeUserAdapter = activeUserAdapter;
    }


    public interface OnChannelItemClicked {
        void click(ChannelItem channel, ChannelItemViewHolder holder);
    };

    private OnChannelItemClicked onChannelItemClicked;

    public void setOnChannelItemClicked(OnChannelItemClicked onChannelItemClicked) {
        this.onChannelItemClicked = onChannelItemClicked;
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout relativeLayout;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.channelList);
        }
    }
    public class SearchBarViewHolder extends ChannelViewHolder {
        public SearchBarBinding searchBarBinding;
        public SearchBarViewHolder(@NonNull SearchBarBinding searchBarBinding) {
            super(searchBarBinding.getRoot());
            this.searchBarBinding = searchBarBinding;
        }
    }

    public class ActiveUserBarViewHolder extends ChannelViewHolder {
        public ActiveUserBarBinding activeUserBarBinding;
        public ActiveUserBarViewHolder(@NonNull ActiveUserBarBinding activeUserBarBinding) {
            super(activeUserBarBinding.getRoot());
            this.activeUserBarBinding = activeUserBarBinding;
        }
    }

    public class ChannelItemViewHolder extends ChannelViewHolder {
        public ChannelItemBinding channelItemBinding;
        public ChannelItemViewHolder(@NonNull ChannelItemBinding channelItemBinding) {
            super(channelItemBinding.getRoot());
            this.channelItemBinding = channelItemBinding;
        }
    }

    private DiffUtil.ItemCallback<ChannelItem> differCallback = new DiffUtil.ItemCallback<ChannelItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChannelItem oldItem, @NonNull ChannelItem newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChannelItem oldItem, @NonNull ChannelItem newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public AsyncListDiffer<ChannelItem> differ = new AsyncListDiffer<ChannelItem>(this, differCallback);

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SEARCH_BAR) return new SearchBarViewHolder(
                SearchBarBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );

        if (viewType == ACTIVE_USER_BAR) return new ActiveUserBarViewHolder(
                ActiveUserBarBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );

        return new ChannelItemViewHolder(
                ChannelItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return SEARCH_BAR;
        if (position == 1) return ACTIVE_USER_BAR;
        return CHANNEL_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        ChannelItem channelItem = differ.getCurrentList().get(position);

        if (position == 0) {
            SearchBarViewHolder searchBarViewHolder = (SearchBarViewHolder) holder;
            searchBarViewHolder.searchBarBinding.searchBar.setOnClickListener( (view) -> {
                onItemClicked(channelItem, holder);
            });
            return;
        }
        if (position == 1) {
            ActiveUserBarViewHolder activeUserBarViewHolder = (ActiveUserBarViewHolder) holder;

            activeUserBarViewHolder.activeUserBarBinding.rcActiveUser.setAdapter(
                    activeUserAdapter
            );

            activeUserBarViewHolder.activeUserBarBinding.rcActiveUser.setOnClickListener( (view) -> {
                onItemClicked(channelItem, holder);
            });
            return;
        }

        ChannelItemViewHolder channelItemViewHolder = (ChannelItemViewHolder) holder;
        channelItemViewHolder.channelItemBinding.setChannelItem(differ.getCurrentList().get(position));

        channelItemViewHolder.channelItemBinding.getRoot().setOnClickListener( (view) -> {
                onItemClicked(channelItem, holder);
        });
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public void onItemClicked(ChannelItem channelItem, ChannelViewHolder holder) {

        Log.i("Search bar", "channel: ");

        if (channelItem.type == ChannelItem.CHANNEL) {
            onChannelItemClicked.click(channelItem, (ChannelItemViewHolder) holder);
        }


        if (channelItem.type == ChannelItem.SEARCH_BAR) {
            onSearchBarClicked.click();
        }
        if (channelItem.type == ChannelItem.ACTIVE_USER_BAR) {
            onActiveUserBarClicked.click();
        }

    }
}