package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.ActiveUserBarBinding;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.databinding.SearchBarBinding;
import com.tungngt.dev.model.ChannelItem;

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> {
    public static int SEARCH_BAR = 0;
    public static int ACTIVE_USER_BAR = 1;
    public static int CHANNEL_ITEM = 2;


    public ActiveUserAdapter activeUserAdapter;

    public ChannelAdapter(ActiveUserAdapter activeUserAdapter) {
        this.activeUserAdapter = activeUserAdapter;
    }


    public class ChannelViewHolder extends RecyclerView.ViewHolder {
        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
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
        if (position == 0) {
            SearchBarViewHolder searchBarViewHolder = (SearchBarViewHolder) holder;

            return;
        }
        if (position == 1) {
            ActiveUserBarViewHolder activeUserBarViewHolder = (ActiveUserBarViewHolder) holder;

            activeUserBarViewHolder.activeUserBarBinding.rcActiveUser.setAdapter(
                    activeUserAdapter
            );
            return;
        }

        ChannelItemViewHolder channelItemViewHolder = (ChannelItemViewHolder) holder;
        channelItemViewHolder.channelItemBinding.setChannelItem(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}