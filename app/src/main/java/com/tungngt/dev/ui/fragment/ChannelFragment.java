package com.tungngt.dev.ui.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import androidx.core.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.databinding.FragmentChannelBinding;


import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.model.MainRecyclerViewItem;
import com.tungngt.dev.ui.activity.ChatActivity;
import com.tungngt.dev.ui.activity.SearchActivity;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;

import com.tungngt.dev.ui.adapter.MainRecyclerViewAdapter;
import com.tungngt.dev.ui.bottomsheets.AddChannelBottomSheet;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ChannelFragment extends BaseMainFragment {
    private FragmentChannelBinding fragmentChannelBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChannelBinding = FragmentChannelBinding.inflate(inflater);
        fragmentChannelBinding.addChannel.setOnClickListener((view) -> {
                // Open the bottom sheet when "addChannel" is clicked
                showAddChannelBottomSheet();
        });


        ActiveUserAdapter activeUserAdapter = new ActiveUserAdapter();

        List<ActiveUser> activeUserList = new ArrayList<>();
        activeUserList.add(new ActiveUser("Thanh Tung", "123", 0xFF78281F));
        activeUserList.add(new ActiveUser("Hoai Nhi", "123", 0xFFFF4E50));
        activeUserList.add(new ActiveUser("Viet Tung", "123", 0xFF07575B));
        activeUserList.add(new ActiveUser("Xuan Tung", "123", 0xFF727077));
        activeUserList.add(new ActiveUser("Dang Son", "123", 0xFFE99787));
        activeUserList.add(new ActiveUser("Minh Tung ", "123", 0xFF90AFC5));
        activeUserList.add(new ActiveUser("Nguyen Phan Gia Bao", "123", 0xFF76448A));
        activeUserList.add(new ActiveUser("Ta Quang Sang", "123", 0xFF943128));
        activeUserList.add(new ActiveUser("Duong", "123", 0xFF78281F));
        activeUserList.add(new ActiveUser("Minh Vu", "123", 0xFFFF4E50));
        activeUserList.add(new ActiveUser("Can Trung Hieu", "123", 0xFF07575B));
        activeUserList.add(new ActiveUser("Chu Bao Minh", "123", 0xFF727077));

        MainRecyclerViewAdapter rvAdapter = new MainRecyclerViewAdapter(
                activeUserAdapter
        );
        fragmentChannelBinding.channelList.setAdapter(rvAdapter);

        rvAdapter.setOnSearchBarClicked(() -> {
            Intent intent = new Intent(requireContext(), SearchActivity.class);
            startActivity(intent);

        });

        rvAdapter.setOnActiveUserBarClicked(() -> {
            // TODO: implement click active user
        });


        rvAdapter.setOnChannelItemClicked(this::onChannelItemClicked);

        getMainViewModel()
                .loadSavedRcChannels(getAppContainer().getCurrentServer())
                .observe(getViewLifecycleOwner(), channels -> {
                    List<MainRecyclerViewItem> list = new ArrayList<>();
                    list.add(new MainRecyclerViewItem.SearchBar());
                    list.add(new MainRecyclerViewItem.ActiveUserBar());
                    list.addAll(channels);
                    rvAdapter.differ.submitList(list);
                });

        activeUserAdapter.differ.submitList(activeUserList);



        return fragmentChannelBinding.getRoot();
    }

    private void showAddChannelBottomSheet() {
        AddChannelBottomSheet addChannelBottomSheet = new AddChannelBottomSheet();
        addChannelBottomSheet.setOnAddListener(this::onAdd);
        addChannelBottomSheet.show(
                requireActivity().getSupportFragmentManager(),
                AddChannelBottomSheet.TAG
        );
    }

    private void onChannelItemClicked(
            ChannelEntity channel,
            MainRecyclerViewAdapter.ChannelItemViewHolder holder
    ) {
        ChannelItemBinding channelItemBinding = holder.channelItemBinding;
        Intent intent = new Intent(getContext(), ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("channel", channel);
        intent.putExtras(bundle);

        Pair<View, String>[] sharedTransitionPairs = new Pair[2];

        sharedTransitionPairs[0] = new Pair<>(
                channelItemBinding.channelTitle,
                ViewCompat.getTransitionName(channelItemBinding.channelTitle)
        );

        sharedTransitionPairs[1] = new Pair<>(
                channelItemBinding.channelImage,
                ViewCompat.getTransitionName(channelItemBinding.channelImage)
        );

        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(),
                sharedTransitionPairs
        );
        startActivity(intent, optionsCompat.toBundle());
    }
    public void onAdd(String handle, String name, Integer color, String description) {
        getMainViewModel().addChannel(
                getAppContainer().getCurrentServer().getId(),
                handle,
                name,
                color,
                description
        );
    }
}