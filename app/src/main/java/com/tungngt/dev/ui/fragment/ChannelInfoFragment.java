package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tungngt.dev.databinding.FragmentChatInfomationBinding;


public class ChannelInfoFragment extends Fragment {

    private FragmentChatInfomationBinding fragmentChatInfomationBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChatInfomationBinding = FragmentChatInfomationBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentChatInfomationBinding.getRoot();
    }

}