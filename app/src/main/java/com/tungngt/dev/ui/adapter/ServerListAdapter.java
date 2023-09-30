package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.databinding.ServerItemBinding;

public class ServerListAdapter extends RecyclerView.Adapter<ServerListAdapter.ServerListViewHolder> {

    public interface OnServerClicked {
        void click(ServerEntity server, ServerListViewHolder holder);
    };

    private OnServerClicked onServerClicked;

    public void setOnServerClicked(OnServerClicked onServerClicked) {
        this.onServerClicked = onServerClicked;
    }

    public class ServerListViewHolder extends RecyclerView.ViewHolder {
        public ServerItemBinding serverItemBinding;
        public ServerListViewHolder(@NonNull ServerItemBinding serverItemBinding) {
            super(serverItemBinding.getRoot());
            this.serverItemBinding = serverItemBinding;
        }
    }

    private DiffUtil.ItemCallback<ServerEntity> differCallback = new DiffUtil.ItemCallback<ServerEntity>() {
        @Override
        public boolean areItemsTheSame(@NonNull ServerEntity oldItem, @NonNull ServerEntity newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ServerEntity oldItem, @NonNull ServerEntity newItem) {
            return oldItem.equals(newItem);
        }
    };

    public AsyncListDiffer<ServerEntity> differ = new AsyncListDiffer<ServerEntity>(this, differCallback);


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
        ServerEntity server = differ.getCurrentList().get(position);
        holder.serverItemBinding.setServer(server);

        holder.serverItemBinding.getRoot().setOnClickListener((view) -> {
            onServerClicked.click(server, holder);
        });
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}