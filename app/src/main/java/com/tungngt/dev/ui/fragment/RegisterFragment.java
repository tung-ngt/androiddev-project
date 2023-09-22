package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.tungngt.dev.R;
import com.tungngt.dev.databinding.FragmentRegisterBinding;

import java.util.Objects;


public class RegisterFragment extends Fragment{
    private FragmentRegisterBinding fragmentRegisterBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentRegisterBinding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRegisterBinding = FragmentRegisterBinding.inflate(getLayoutInflater());

        fragmentRegisterBinding.signupButton.setOnClickListener(this::signup);
    }

    private void signup(View view) {
        NavController navController = NavHostFragment.findNavController(
                Objects.requireNonNull(
                        requireActivity()
                                .getSupportFragmentManager()
                                .findFragmentById(R.id.nav_host_fragment_container)
                )
        );
        navController.popBackStack();
    }
}