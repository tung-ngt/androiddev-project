package com.tungngt.dev.ui.fragment;

import static com.tungngt.dev.BR.channelItem;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentChannelBinding;


import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;

import com.tungngt.dev.model.ChannelItem;
import com.tungngt.dev.ui.adapter.ChannelAdapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
                activeUserAdapter, getContext()
        );
        fragmentChannelBinding.channelList.setAdapter(channelAdapter);

        channelAdapter.setOnSearchBarClicked(() -> {

            NavController navController = NavHostFragment.findNavController(
                    Objects.requireNonNull(requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment))
            );

            navController.navigate(R.id.action_channelFrag_to_chatActivity);

        });

        channelAdapter.setOnActiveUserBarClicked(() -> {
            // TODO: implement click active user
        });




        List<ChannelItem> channelItemList = new ArrayList<>();
        ChannelItem searchBar = new ChannelItem("1" ,"searchbar", "123", "Thanh Tung ", " something  ", "2 days");
        searchBar.type = ChannelItem.SEARCH_BAR;
        channelItemList.add(searchBar);

        ChannelItem activeUser = new ChannelItem("1" ,"searchbar", "123", "Thanh Tung ", " something  ", "2 days");
        activeUser.type = ChannelItem.ACTIVE_USER_BAR;
        channelItemList.add(activeUser);

        channelItemList.add(new ChannelItem("3" ,"main", "123", "Thanh Tung: ", " something  ", "2 days"));
        channelItemList.add(new ChannelItem("4" ,"help", "123", "Viet Tung:", "  something   ", "2 days"));
        channelItemList.add(new ChannelItem("5" ,"resources", "123", "D. Thanh Tung:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("6" ,"pinned", "123", "Minh Tung:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("7" ,"tech", "123", "Sang:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("8" ,"music", "123", "Huy:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("9" ,"movies", "123", "user1:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("10" ,"lounge", "123", "user2:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("11" ,"gaming", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("12" ,"gaming1", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("13" ,"gaming2", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("14" ,"gaming3", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("15" ,"gaming4", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("16" ,"gaming5", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("17" ,"gaming6", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("18" ,"gaming7", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("19" ,"gaming8", "123", "user3:", "  something  ", "2 days"));
        channelItemList.add(new ChannelItem("20" ,"gaming9", "123", "user3:", "  something  ", "2 days"));

        channelAdapter.differ.submitList(channelItemList);
        activeUserAdapter.differ.submitList(activeUserList);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentChannelBinding.getRoot();
    }
}