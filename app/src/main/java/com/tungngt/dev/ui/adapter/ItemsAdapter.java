package com.tungngt.dev.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tungngt.dev.databinding.SimpleItemBinding;
import com.tungngt.dev.model.Person;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public SimpleItemBinding simpleItemBinding;
        public ItemViewHolder(@NonNull SimpleItemBinding simpleItemBinding) {
            super(simpleItemBinding.getRoot());
            this.simpleItemBinding = simpleItemBinding;
        }
    }

    private DiffUtil.ItemCallback<Person> differCallback = new DiffUtil.ItemCallback<Person>() {
        @Override
        public boolean areItemsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.name.equals(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull Person oldItem, @NonNull Person newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public AsyncListDiffer<Person> differ = new AsyncListDiffer(this, differCallback);

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(
                SimpleItemBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.simpleItemBinding.setPerson(differ.getCurrentList().get(position));
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }
}
