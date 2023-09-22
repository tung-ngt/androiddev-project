package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.ServerItemBinding;
import com.tungngt.dev.model.Server;

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerListViewHolder> {
    public class ServerListViewHolder extends RecyclerView.ViewHolder {
        public ServerItemBinding serverItemBinding;
        public ServerListViewHolder(@NonNull ServerItemBinding serverItemBinding) {
            super(serverItemBinding.getRoot());
            this.serverItemBinding = serverItemBinding;
        }
    }

    private DiffUtil.ItemCallback<Server> differCallback = new DiffUtil.ItemCallback<Server>() {
        @Override
        public boolean areItemsTheSame(@NonNull Server oldItem, @NonNull Server newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Server oldItem, @NonNull Server newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public AsyncListDiffer<Server> differ = new AsyncListDiffer<Server>(this, differCallback);


    @NonNull
    @Override
    public ServerListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServerListViewHolder(
                ServerItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ServerListViewHolder holder, int position) {
        holder.serverItemBinding.setServer(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}