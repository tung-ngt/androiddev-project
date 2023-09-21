package com.tungngt.dev.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.MessageBinding;
import com.tungngt.dev.model.Message;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    public class ChatViewHolder extends RecyclerView.ViewHolder {
        public MessageBinding messageBinding;
        public ChatViewHolder(@NonNull MessageBinding messageBinding) {
            super(messageBinding.getRoot());
            this.messageBinding = messageBinding;
        }
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChatViewHolder(
                MessageBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    private DiffUtil.ItemCallback<Message> differCallback = new DiffUtil.ItemCallback<Message>() {
        @Override
        public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public AsyncListDiffer<Message> differ = new AsyncListDiffer<Message>(this, differCallback);

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.messageBinding.setMessage(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}