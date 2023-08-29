package com.tungngt.dev.ui.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.tungngt.dev.model.Person;

import java.util.List;

public class ItemDiffUtilCallback extends DiffUtil.Callback {
    List<Person> newList;
    List<Person> oldList;
    public ItemDiffUtilCallback(List<Person> newList, List<Person> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }
    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).compareTo(newList.get(newItemPosition)) == 0;
    }
}
