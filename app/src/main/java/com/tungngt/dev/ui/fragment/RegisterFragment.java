package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentRegisterBinding;


public class RegisterFragment extends Fragment{
    private FragmentRegisterBinding fragmentRegisterBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(getLayoutInflater());
    }
    public RegisterFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}