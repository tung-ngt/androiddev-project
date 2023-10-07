package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.tungngt.dev.databinding.FragmentChannelPeopleBinding;
import com.tungngt.dev.ui.adapter.ActiveUserServerAdapter;

import java.util.ArrayList;


public class ChannelPeopleFragment extends BaseChatSettingFragment {

    private FragmentChannelPeopleBinding fragmentChannelPeopleBinding;
    private ActiveUserServerAdapter activeUserAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentChannelPeopleBinding = FragmentChannelPeopleBinding.inflate(getLayoutInflater());

        activeUserAdapter = new ActiveUserServerAdapter();
        fragmentChannelPeopleBinding.rcActiveUserInServer.setAdapter(activeUserAdapter);



        return fragmentChannelPeopleBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getChatSettingViewModel().requestActiveUser(getAppContainer().getCurrentChannel());
        getChatSettingViewModel().getActiveUsers().observe(getViewLifecycleOwner(), activeUsers -> {
            activeUserAdapter.differ.submitList(new ArrayList<>(activeUsers));
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        getChatSettingViewModel().cancelRequestActiveUser();
    }
}