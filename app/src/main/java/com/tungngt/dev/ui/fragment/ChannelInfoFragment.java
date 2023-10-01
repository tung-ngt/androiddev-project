package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.tungngt.dev.databinding.FragmentChatInfomationBinding;


public class ChannelInfoFragment extends BaseChatSettingFragment {

    private FragmentChatInfomationBinding fragmentChatInfomationBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentChatInfomationBinding = FragmentChatInfomationBinding.inflate(getLayoutInflater());

        return fragmentChatInfomationBinding.getRoot();
    }

}