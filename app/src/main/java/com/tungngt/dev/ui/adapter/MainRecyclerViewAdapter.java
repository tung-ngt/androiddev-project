package com.tungngt.dev.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ActiveUserBarBinding;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.databinding.SearchBarBinding;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.model.MainRecyclerViewItem;

public class MainRecyclerViewAdapter
        extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainItemViewHolder>
{
    // ActiveUserBarAdapter
    public ActiveUserAdapter activeUserAdapter;

    // ActiveUserBarClick handler
    public interface OnActiveUserBarClicked {
        void click();
    };
    private OnActiveUserBarClicked onActiveUserBarClicked;
    public void setOnActiveUserBarClicked(OnActiveUserBarClicked onActiveUserBarClicked) {
        this.onActiveUserBarClicked = onActiveUserBarClicked;
    }
    public MainRecyclerViewAdapter(ActiveUserAdapter activeUserAdapter) {
        this.activeUserAdapter = activeUserAdapter;
    }



    // SearchBar click handler
    public interface OnSearchBarClicked {
        void click();
    };
    private OnSearchBarClicked onSearchBarClicked;
    public void setOnSearchBarClicked(OnSearchBarClicked onSearchBarClicked) {
        this.onSearchBarClicked = onSearchBarClicked;
    }



    // Channel click handler
    public interface OnChannelItemClicked {
        void click(ChannelEntity channel, ChannelItemViewHolder holder);
    };
    private OnChannelItemClicked onChannelItemClicked;
    public void setOnChannelItemClicked(OnChannelItemClicked onChannelItemClicked) {
        this.onChannelItemClicked = onChannelItemClicked;
    }



    public class MainItemViewHolder extends RecyclerView.ViewHolder {
        public MainItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public class SearchBarViewHolder extends MainItemViewHolder {
        public SearchBarBinding searchBarBinding;
        public SearchBarViewHolder(@NonNull SearchBarBinding searchBarBinding) {
            super(searchBarBinding.getRoot());
            this.searchBarBinding = searchBarBinding;
        }
    }

    public class ActiveUserBarViewHolder extends MainItemViewHolder {
        public ActiveUserBarBinding activeUserBarBinding;
        public ActiveUserBarViewHolder(@NonNull ActiveUserBarBinding activeUserBarBinding) {
            super(activeUserBarBinding.getRoot());
            this.activeUserBarBinding = activeUserBarBinding;
        }
    }

    public class ChannelItemViewHolder extends MainItemViewHolder {
        public ChannelItemBinding channelItemBinding;
        public ChannelItemViewHolder(@NonNull ChannelItemBinding channelItemBinding) {
            super(channelItemBinding.getRoot());
            this.channelItemBinding = channelItemBinding;
        }
    }



    // DiffUtil setup
    private DiffUtil.ItemCallback<MainRecyclerViewItem> differCallback = new DiffUtil.ItemCallback<MainRecyclerViewItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull MainRecyclerViewItem oldItem, @NonNull MainRecyclerViewItem newItem) {
            boolean sameType = oldItem.sameType(newItem);
            if (!sameType) return false;

            if (oldItem.getViewType() == MainRecyclerViewItem.CHANNEL) {
                MainRecyclerViewItem.Channel oldChannel = (MainRecyclerViewItem.Channel) oldItem;
                MainRecyclerViewItem.Channel newChannel = (MainRecyclerViewItem.Channel) newItem;
                return oldChannel.channelEntity.getId().equals(newChannel.channelEntity.getId());
            }

            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull MainRecyclerViewItem oldItem, @NonNull MainRecyclerViewItem newItem) {
            return oldItem.equals(newItem);
        }
    };

    public AsyncListDiffer<MainRecyclerViewItem> differ = new AsyncListDiffer<MainRecyclerViewItem>(this, differCallback);



    @NonNull
    @Override
    public MainItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MainRecyclerViewItem.SEARCH_BAR) return new SearchBarViewHolder(
                SearchBarBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );

        if (viewType == MainRecyclerViewItem.ACTIVE_USER_BAR) return new ActiveUserBarViewHolder(
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
        if (position == 0) return MainRecyclerViewItem.SEARCH_BAR;
        if (position == 1) return MainRecyclerViewItem.ACTIVE_USER_BAR;
        return MainRecyclerViewItem.CHANNEL;
    }

    @Override
    public void onBindViewHolder(@NonNull MainItemViewHolder holder, int position) {
        MainRecyclerViewItem channelItem = differ.getCurrentList().get(position);

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
        channelItemViewHolder.channelItemBinding.setChannel(
                ((MainRecyclerViewItem.Channel) differ.getCurrentList().get(position)).channelEntity
        );

        channelItemViewHolder.channelItemBinding.getRoot().setOnClickListener( (view) -> {
                onItemClicked(channelItem, holder);
        });
        channelItemViewHolder.channelItemBinding.layoutForeground.setOnClickListener( (view) -> {
            onItemClicked(channelItem, holder);
        });
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }



    public void onItemClicked(MainRecyclerViewItem channelItem, MainItemViewHolder holder) {

        Log.i("Search bar", "channel: ");

        if (channelItem.getViewType() == MainRecyclerViewItem.CHANNEL) {
            onChannelItemClicked.click(
                    ((MainRecyclerViewItem.Channel) channelItem).channelEntity,
                    (ChannelItemViewHolder) holder
            );
        }

        if (channelItem.getViewType() == MainRecyclerViewItem.SEARCH_BAR) {
            onSearchBarClicked.click();
        }

        if (channelItem.getViewType() == MainRecyclerViewItem.ACTIVE_USER_BAR) {
            onActiveUserBarClicked.click();
        }

    }
}