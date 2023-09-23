package com.tungngt.dev.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.databinding.FragmentPeopleBinding;
import com.tungngt.dev.model.ActiveUser;
import com.tungngt.dev.ui.adapter.ActiveUserAdapter;
import com.tungngt.dev.ui.adapter.ActiveUserServerAdapter;

import java.util.ArrayList;
import java.util.List;


public class PeopleFragment extends Fragment {

    private FragmentPeopleBinding fragmentPeopleBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentPeopleBinding = FragmentPeopleBinding.inflate(getLayoutInflater());

        ActiveUserServerAdapter activeUserAdapter = new ActiveUserServerAdapter();
        fragmentPeopleBinding.rcActiveUserInServer.setAdapter(activeUserAdapter);

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

        activeUserAdapter.differ.submitList(activeUserList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentPeopleBinding.getRoot();
    }

}