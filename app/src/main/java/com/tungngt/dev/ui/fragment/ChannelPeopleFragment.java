package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tungngt.dev.databinding.FragmentChannelPeopleBinding;
import com.tungngt.dev.databinding.FragmentPeopleBinding;
import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.ui.adapter.ActiveUserServerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ChannelPeopleFragment extends Fragment {

    private FragmentChannelPeopleBinding fragmentChannelPeopleBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentChannelPeopleBinding = FragmentChannelPeopleBinding.inflate(getLayoutInflater());

        ActiveUserServerAdapter activeUserAdapter = new ActiveUserServerAdapter();
        fragmentChannelPeopleBinding.rcActiveUserInServer.setAdapter(activeUserAdapter);

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

        activeUserAdapter.differ.submitList(activeUserList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentChannelPeopleBinding.getRoot();
    }

}