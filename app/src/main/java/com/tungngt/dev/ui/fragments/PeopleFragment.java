package com.tungngt.dev.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.databinding.FragmentPeopleBinding;


public class PeopleFragment extends Fragment {

    private FragmentPeopleBinding fragmentPeopleBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentPeopleBinding = FragmentPeopleBinding.inflate(getLayoutInflater());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentPeopleBinding.getRoot();
    }

}