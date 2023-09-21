package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentUserSettingsBinding;

public class UserSettingsFragment extends Fragment {
    private FragmentUserSettingsBinding fragmentUserSettingsBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentUserSettingsBinding = FragmentUserSettingsBinding.inflate(getLayoutInflater());
    }

    public UserSettingsFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FragmentView = inflater.inflate(R.layout.fragment_user_settings, container, false);
        return FragmentView;
    }
}
