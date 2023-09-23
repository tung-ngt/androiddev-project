package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.SearchRcChannelBinding;
import com.tungngt.dev.databinding.SearchAllChannelItemBinding;
import com.tungngt.dev.databinding.NewSearchBarBinding;
import com.tungngt.dev.model.Searching;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    public static int NEW_SEARCH_BAR = 0;
    public static int RC_CHANNEL_BAR = 1;
    public static int CHANNEL_ITEM = 2;


    public SearchAdapter searchAdapter;

    public SearchAdapter() {
    }

    public SearchAdapter(SearchAdapter searchAdapter) {
        this.searchAdapter = searchAdapter;
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class SearchBarViewHolder extends SearchViewHolder {
        public NewSearchBarBinding newSearchBarBinding;
        public SearchBarViewHolder(@NonNull NewSearchBarBinding newSearchBarBinding) {
            super(newSearchBarBinding.getRoot());
            this.newSearchBarBinding = newSearchBarBinding;
        }
    }

    public class RcChannelViewHolder extends SearchViewHolder {
        public SearchRcChannelBinding rcChannelBinding;
        public RcChannelViewHolder(@NonNull SearchRcChannelBinding rcChannelBinding) {
            super(rcChannelBinding.getRoot());
            this.rcChannelBinding = rcChannelBinding;
        }
    }

    public class ChannelItemViewHolder extends SearchViewHolder {
        public SearchAllChannelItemBinding searchAllChannelItemBinding;
        public ChannelItemViewHolder(@NonNull SearchAllChannelItemBinding searchAllChannelItemBinding) {
            super(searchAllChannelItemBinding.getRoot());
            this.searchAllChannelItemBinding = searchAllChannelItemBinding;
        }
    }

    private DiffUtil.ItemCallback<Searching> differCallback = new DiffUtil.ItemCallback<Searching>() {
        @Override
        public boolean areItemsTheSame(@NonNull Searching oldItem, @NonNull Searching newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Searching oldItem, @NonNull Searching newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public AsyncListDiffer<Searching> differ = new AsyncListDiffer<Searching>(this, differCallback);

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NEW_SEARCH_BAR) return new SearchBarViewHolder(
                NewSearchBarBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );

        if (viewType == RC_CHANNEL_BAR) return new RcChannelViewHolder(
                SearchRcChannelBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );

        return new ChannelItemViewHolder(
                SearchAllChannelItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return NEW_SEARCH_BAR;
        if (position == 1) return RC_CHANNEL_BAR;
        return CHANNEL_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        if (position == 0) {
            SearchBarViewHolder searchBarViewHolder = (SearchBarViewHolder) holder;

            return;
        }
        if (position == 1) {
            RcChannelViewHolder activeUserBarViewHolder = (RcChannelViewHolder) holder;

            activeUserBarViewHolder.rcChannelBinding.rcActiveChannel.setAdapter(
                    searchAdapter
            );
            return;
        }

        ChannelItemViewHolder channelItemViewHolder = (ChannelItemViewHolder) holder;
        channelItemViewHolder.searchAllChannelItemBinding.setActiveAllChannel(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}