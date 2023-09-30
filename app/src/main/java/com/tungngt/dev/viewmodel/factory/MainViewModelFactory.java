package com.tungngt.dev.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.data.repository.MainRepository;
import com.tungngt.dev.viewmodel.MainViewModel;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private final MainRepository mainRepository;

    public MainViewModelFactory(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(mainRepository);
    }
}
