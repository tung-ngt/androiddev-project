package com.tungngt.dev.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.data.repository.ServerRepository;
import com.tungngt.dev.viewmodel.ServerListViewModel;

public class ServerListViewModelFactory implements ViewModelProvider.Factory {
    private final ServerRepository serverRepository;

    public ServerListViewModelFactory(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ServerListViewModel(serverRepository);
    }
}
