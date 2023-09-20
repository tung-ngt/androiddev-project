package com.tungngt.dev.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.databinding.FragmentChannelBinding;


import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;

import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.ui.adapter.ChannelAdapter;


import java.util.ArrayList;
import java.util.List;

import com.tungngt.dev.R;
import android.view.View.OnClickListener;
import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import com.google.android.material.textfield.TextInputEditText;

public class ChannelFragment extends Fragment {
    private FragmentChannelBinding fragmentChannelBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChannelBinding = FragmentChannelBinding.inflate(getLayoutInflater());

        ActiveUserAdapter activeUserAdapter = new ActiveUserAdapter();

        List<ActiveUser> activeUserList = new ArrayList<>();
        activeUserList.add(new ActiveUser("Thanh Tung", "123"));
        activeUserList.add(new ActiveUser("Hoai Nhi", "123"));
        activeUserList.add(new ActiveUser("Viet Tung", "123"));
        activeUserList.add(new ActiveUser("Xuan Tung", "123"));
        activeUserList.add(new ActiveUser("Dang Son", "123"));
        activeUserList.add(new ActiveUser("Minh Tung ", "123"));
        activeUserList.add(new ActiveUser("Nguyen Phan Gia Bao", "123"));
        activeUserList.add(new ActiveUser("Ta Quang Sang", "123"));
        activeUserList.add(new ActiveUser("Duong", "123"));
        activeUserList.add(new ActiveUser("Minh Vu", "123"));
        activeUserList.add(new ActiveUser("Can Trung Hieu", "123"));
        activeUserList.add(new ActiveUser("Chu Bao Minh", "123"));

        ChannelAdapter channelAdapter = new ChannelAdapter(
                activeUserAdapter
        );
        fragmentChannelBinding.channelList.setAdapter(channelAdapter);

        List<ChannelItem> channelItemList = new ArrayList<>();
        channelItemList.add(new ChannelItem("searchbar", "123", "Thanh Tung ", " something  ", "2 days"));
        channelItemList.add(new ChannelItem("activeUser", "123", "Thanh Tung:", "  something   ", "2 days"));
        channelItemList.add(new ChannelItem("main", "123", "Thanh Tung: ", " something  ", "2 days"));
        channelItemList.add(new ChannelItem("help", "123", "Viet Tung:", "  something   ", "2 days"));
        channelItemList.add(new ChannelItem("resources", "123", "D. Thanh Tung:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("pinned", "123", "Minh Tung:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("tech", "123", "Sang:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("music", "123", "Huy:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("movies", "123", "user1:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("lounge", "123", "user2:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming1", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming2", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming3", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming4", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming5", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming6", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming7", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming8", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("gaming9", "123", "user3:", "  something  ", "2 days"));

        channelAdapter.differ.submitList(channelItemList);
        activeUserAdapter.differ.submitList(activeUserList);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentChannelBinding.getRoot();
    }}