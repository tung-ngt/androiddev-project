package com.tungngt.dev.ui.fragment;


import android.content.ClipData;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.search.SearchBar;
import com.google.android.material.snackbar.Snackbar;
import com.tungngt.dev.R;
import com.tungngt.dev.databinding.ChannelItemBinding;
import com.tungngt.dev.databinding.FragmentChannelBinding;
import com.tungngt.dev.databinding.SearchBarBinding;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.model.MainRecyclerViewItem;
import com.tungngt.dev.ui.activity.ChatActivity;
import com.tungngt.dev.ui.activity.ChatSettingActivity;
import com.tungngt.dev.ui.activity.SearchActivity;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;
import com.tungngt.dev.ui.adapter.MainRecyclerViewAdapter;
import com.tungngt.dev.ui.bottomsheets.AddChannelBottomSheet;

import java.util.ArrayList;
import java.util.List;


public class ChannelFragment extends BaseMainFragment {
    private FragmentChannelBinding fragmentChannelBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentChannelBinding = FragmentChannelBinding.inflate(inflater);
        fragmentChannelBinding.addChannel.setOnClickListener((view) -> {
                showAddChannelBottomSheet();
        });


        ActiveUserAdapter activeUserAdapter = new ActiveUserAdapter();

        List<ActiveUser> activeUserList = new ArrayList<>();
        activeUserList.add(new ActiveUser("Thanh Tung"));
        activeUserList.add(new ActiveUser("Hoai Nhi"));
        activeUserList.add(new ActiveUser("Viet Tung"));
        activeUserList.add(new ActiveUser("Xuan Tung"));
        activeUserList.add(new ActiveUser("Dang Son"));
        activeUserList.add(new ActiveUser("Minh Tung "));
        activeUserList.add(new ActiveUser("Nguyen Phan Gia Bao"));
        activeUserList.add(new ActiveUser("Ta Quang Sang"));
        activeUserList.add(new ActiveUser("Duong"));
        activeUserList.add(new ActiveUser("Minh Vu"));
        activeUserList.add(new ActiveUser("Can Trung Hieu"));
        activeUserList.add(new ActiveUser("Chu Bao Minh"));

        MainRecyclerViewAdapter rvAdapter = new MainRecyclerViewAdapter(
                activeUserAdapter
        );
        fragmentChannelBinding.channelList.setAdapter(rvAdapter);

        rvAdapter.setOnSearchBarClicked(() -> {
            Intent intent = new Intent(requireContext(), SearchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("searchType", SearchActivity.CHANNEL);
            intent.putExtras(bundle);
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        // if user touch on search bar, disable swipe
        fragmentChannelBinding.channelList.setOnTouchListener((view, motionEvent) -> {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    itemTouchHelper.attachToRecyclerView(null);
                    break;
                case MotionEvent.ACTION_MOVE:
                    // check if user touch on search bar
                    SearchBar searchBar = fragmentChannelBinding.channelList.findViewById(R.id.search_bar);
                    ConstraintLayout activeUserBar = fragmentChannelBinding.channelList.findViewById(R.id.active_user_bar);
                    // if user touch on search bar, disable swipe
//                    if (searchBar != null || activeUserBar != null) {
                        float x = motionEvent.getX();
                        float y = motionEvent.getY();
                        if (x >= 0 && x <= searchBar.getRight()
                                && y >= searchBar.getTop() && y <= searchBar.getBottom()) {
                            itemTouchHelper.attachToRecyclerView(null);
                        } else
                        if (x >= 0 && x <= activeUserBar.getRight()
                                && y >= activeUserBar.getTop() && y <= activeUserBar.getBottom()) {
                            itemTouchHelper.attachToRecyclerView(null);
                        } else {
                            itemTouchHelper.attachToRecyclerView(fragmentChannelBinding.channelList);
                        }

                    break;
            }
            return false;
        });
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

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if ((viewHolder instanceof MainRecyclerViewAdapter.ChannelItemViewHolder) && (direction == ItemTouchHelper.LEFT)) {
                MainRecyclerViewAdapter.ChannelItemViewHolder channelItemViewHolder = (MainRecyclerViewAdapter.ChannelItemViewHolder) viewHolder;
                ChannelEntity channelEntity = channelItemViewHolder.channelItemBinding.getChannel();
                getMainViewModel().storeChannel(channelEntity);
                getMainViewModel().deleteChannel(channelEntity);
                Snackbar.make(
                        fragmentChannelBinding.getRoot(),
                        "Deleted channel " + channelEntity.getName(),
                        Snackbar.LENGTH_LONG
                ).setAction("Undo", (view) -> {
                    getMainViewModel().restoreChannel(channelEntity);;
                }).show();
            }
        }
    };
}