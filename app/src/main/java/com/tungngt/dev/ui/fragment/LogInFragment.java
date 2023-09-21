package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentLoginBinding;
public class LogInFragment extends Fragment {

    private FragmentLoginBinding fragmentLogInBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentLogInBinding = FragmentLoginBinding.inflate(getLayoutInflater());
    }
    public LogInFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View FragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        return FragmentView;
    }

}
