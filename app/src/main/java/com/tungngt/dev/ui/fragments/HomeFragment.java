package com.tungngt.dev.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tungngt.dev.databinding.FragmentHomeBinding;
import com.tungngt.dev.model.Person;
import com.tungngt.dev.ui.adapter.ItemsAdapter;
import com.tungngt.dev.ui.bottomsheets.AddPersonBottomSheet;
import com.tungngt.dev.ui.viewmodel.HomeFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding fragmentHomeBinding;
    private HomeFragmentViewModel homeFragmentViewModel;
    private int index = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        ItemsAdapter itemsAdapter = new ItemsAdapter();

        homeFragmentViewModel.personList.observe(this, personList -> {
            itemsAdapter.differ.submitList(personList);
        });


        fragmentHomeBinding.rvItems.setAdapter(itemsAdapter);
        fragmentHomeBinding.fabAdd.setOnClickListener(view -> {
            AddPersonBottomSheet bottomSheet = new AddPersonBottomSheet();
            bottomSheet.setOnAddListener((name, age) -> {
                assert homeFragmentViewModel.personList.getValue() != null;
                List<Person> personList = new ArrayList<>(homeFragmentViewModel.personList.getValue());
                personList.add(0, new Person(name, age));
                index++;
                homeFragmentViewModel.personList.setValue(personList);
            });
            bottomSheet.show(getParentFragmentManager(), AddPersonBottomSheet.TAG);

        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return fragmentHomeBinding.getRoot();
    }

}