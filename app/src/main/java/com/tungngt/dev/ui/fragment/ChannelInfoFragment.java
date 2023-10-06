package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.tungngt.dev.databinding.FragmentChatInfomationBinding;
import com.tungngt.dev.domain.ChannelEntity;


public class ChannelInfoFragment extends BaseChatSettingFragment {

    private FragmentChatInfomationBinding fragmentChatInfomationBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentChatInfomationBinding = FragmentChatInfomationBinding.inflate(getLayoutInflater());

        // take the channel from the activity and set it to the view model of this fragment

        // take the channel from the activity and set it to the view model of this fragment
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            ChannelEntity channel = (ChannelEntity) extras.getSerializable("channel");

            if (channel != null) {
                fragmentChatInfomationBinding.setChannel(channel);
            }
        }

        return fragmentChatInfomationBinding.getRoot();
    }
}