package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.domain.ChannelEntity;

public class ChannelSearchAdapter
        extends RecyclerView.Adapter<ChannelSearchAdapter.ChannelViewHolder>
{

    // Channel click handler
    public interface OnChannelClicked {
        void click(ChannelEntity channel, ChannelViewHolder holder);
    };
    private OnChannelClicked onChannelClicked;
    public void setOnChannelClicked(OnChannelClicked onChannelClicked) {
        this.onChannelClicked = onChannelClicked;
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {
        public ChannelItemBinding channelItemBinding;
        public ChannelViewHolder(@NonNull ChannelItemBinding channelItemBinding) {
            super(channelItemBinding.getRoot());
            this.channelItemBinding = channelItemBinding;
        }
    }



    // DiffUtil setup
    private DiffUtil.ItemCallback<ChannelEntity> differCallback = new DiffUtil.ItemCallback<ChannelEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ChannelEntity oldItem, @NonNull ChannelEntity newItem) {
                return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ChannelEntity oldItem, @NonNull ChannelEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    public AsyncListDiffer<ChannelEntity> differ = new AsyncListDiffer<ChannelEntity>(this, differCallback);



    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ChannelViewHolder(
                ChannelItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        ChannelEntity channel = differ.getCurrentList().get(position);

        holder.channelItemBinding.setChannel(channel);

        holder.channelItemBinding.getRoot().setOnClickListener( (view) -> {
                onChannelClicked.click(channel, holder);
        });
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}