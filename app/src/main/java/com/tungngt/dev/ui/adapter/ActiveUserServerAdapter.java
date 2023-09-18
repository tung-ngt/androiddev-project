package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.ActiveUserHorizontalItemBinding;
import com.tungngt.dev.model.ActiveUser;

public class ActiveUserServerAdapter extends RecyclerView.Adapter<ActiveUserServerAdapter.ActiveUserViewHolder> {
    public class ActiveUserViewHolder extends RecyclerView.ViewHolder {
        public ActiveUserHorizontalItemBinding activeUserServerItemBinding;
        public ActiveUserViewHolder(@NonNull ActiveUserHorizontalItemBinding activeUserItemBinding) {
            super(activeUserItemBinding.getRoot());
            this.activeUserServerItemBinding = activeUserItemBinding;
        }
    }

    private DiffUtil.ItemCallback<ActiveUser> differCallback = new DiffUtil.ItemCallback<ActiveUser>() {
        @Override
        public boolean areItemsTheSame(@NonNull ActiveUser oldItem, @NonNull ActiveUser newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ActiveUser oldItem, @NonNull ActiveUser newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public AsyncListDiffer<ActiveUser> differ = new AsyncListDiffer<ActiveUser>(this, differCallback);


    @NonNull
    @Override
    public ActiveUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActiveUserViewHolder(
                ActiveUserHorizontalItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveUserViewHolder holder, int position) {
        holder.activeUserServerItemBinding.setActiveUserInServer(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}