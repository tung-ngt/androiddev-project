package com.tungngt.dev.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.databinding.FragmentSearchBinding;


import com.tungngt.dev.model.Searching;
import com.tungngt.dev.ui.adapter.SearchAdapter;


import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private FragmentSearchBinding fragmentSearchBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentSearchBinding = FragmentSearchBinding.inflate(getLayoutInflater());

        SearchAdapter searchAdapter = new SearchAdapter();

        List<Searching> rcChannelList = new ArrayList<>();
        rcChannelList.add(new Searching("1", "Thanh Tung", "123"));
        rcChannelList.add(new Searching("2", "Hoai Nhi", "123"));
        rcChannelList.add(new Searching("3", "Viet Tung", "123"));
        rcChannelList.add(new Searching("4", "Xuan Tung", "123"));
        rcChannelList.add(new Searching("5", "Dang Son", "123"));

        SearchAdapter allChannelAdapter = new SearchAdapter(
                searchAdapter
        );
        fragmentSearchBinding.allChannels.setAdapter(allChannelAdapter);

        List<Searching> channelItemList = new ArrayList<>();
        channelItemList.add(new Searching("1" ,"searchbar", "123"));
        channelItemList.add(new Searching("2" ,"activeUser", "123"));
        channelItemList.add(new Searching("3" ,"main", "123"));
        channelItemList.add(new Searching("4" ,"help", "123"));
        channelItemList.add(new Searching("5" ,"resources", "123"));
        channelItemList.add(new Searching("6" ,"pinned", "123"));
        channelItemList.add(new Searching("7" ,"tech", "123"));
        channelItemList.add(new Searching("8" ,"music", "123"));
        channelItemList.add(new Searching("9" ,"movies", "123"));
        channelItemList.add(new Searching("10" ,"lounge", "123"));
        channelItemList.add(new Searching("11" ,"gaming", "123"));
        channelItemList.add(new Searching("12" ,"gaming1", "123"));
        channelItemList.add(new Searching("13" ,"gaming2", "123"));
        channelItemList.add(new Searching("14" ,"gaming3", "123"));
        channelItemList.add(new Searching("15" ,"gaming4", "123"));
        channelItemList.add(new Searching("16" ,"gaming5", "123"));
        channelItemList.add(new Searching("17" ,"gaming6", "123"));
        channelItemList.add(new Searching("18" ,"gaming7", "123"));
        channelItemList.add(new Searching("19" ,"gaming8", "123"));
        channelItemList.add(new Searching("20" ,"gaming9", "123"));

        allChannelAdapter.differ.submitList(channelItemList);
        searchAdapter.differ.submitList(rcChannelList);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentSearchBinding.getRoot();
    }
}



