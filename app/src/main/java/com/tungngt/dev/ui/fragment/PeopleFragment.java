package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.tungngt.dev.databinding.FragmentPeopleBinding;
import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.ui.adapter.ActiveUserServerAdapter;

import java.util.ArrayList;
import java.util.List;


public class PeopleFragment extends BaseMainFragment {

    private FragmentPeopleBinding fragmentPeopleBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentPeopleBinding = FragmentPeopleBinding.inflate(getLayoutInflater());

        ActiveUserServerAdapter activeUserAdapter = new ActiveUserServerAdapter();
        fragmentPeopleBinding.rcActiveUserInServer.setAdapter(activeUserAdapter);

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

        activeUserAdapter.differ.submitList(activeUserList);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentPeopleBinding.getRoot();
    }

}