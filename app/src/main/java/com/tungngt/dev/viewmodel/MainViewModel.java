package com.tungngt.dev.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.data.repository.MainRepository;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.model.MainRecyclerViewItem;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MainRepository mainRepository;

    public MainViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public LiveData<List<MainRecyclerViewItem.Channel>> loadSavedRcChannels(ServerEntity server) {
        return mainRepository.loadSavedRcChannels(server.getId());
    }

}
