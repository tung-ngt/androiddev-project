package com.tungngt.dev.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.data.repository.SearchRepository;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class SearchViewModel extends ViewModel {
    private SearchRepository searchRepository;
    public SearchViewModel(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
        this.timer = new Timer();
    }

    private MutableLiveData<List<ServerEntity>> filterServerList = new MutableLiveData<>(
            new ArrayList<>()
    );

    public LiveData<List<ServerEntity>> getFilterServerList() {
        return filterServerList;
    }

    public LiveData<List<ServerEntity>> getServerList() {
        return searchRepository.loadServers();
    }

    private MutableLiveData<List<ChannelEntity>> filterChannelList = new MutableLiveData<>(
            new ArrayList<>()
    );

    public LiveData<List<ChannelEntity>> getFilterChannelList() {
        return filterChannelList;
    }

    public LiveData<List<ChannelEntity>> getChannelList() {
        return searchRepository.loadChannels(currentSever.getId());
    }

    private ServerEntity currentSever;
    public void setCurrentSever(ServerEntity server) {
        currentSever = server;
    }

    private Timer timer;

    private String searchText;
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void onSeverSearch(String searchText) {
        setSearchText(searchText);
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (searchText == null || searchText.trim().isEmpty()) {
                    filterServerList.postValue(searchRepository.getServersNow());
                } else {
                    filterServerList.postValue(
                            searchRepository.getServersNow()
                                    .stream()
                                    .filter(server -> server.getName().contains(searchText)
                                            || server.getAddress().contains(searchText)
                                    )
                                    .collect(Collectors.toList())
                    );
                }
            }
        }, 500);
    }

    public void connectToServer(ServerEntity server) {
        searchRepository.connectToServer(server);
    }

    public void onChannelSearch(String searchText) {
        setSearchText(searchText);
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (searchText == null || searchText.trim().isEmpty()) {
                    filterChannelList.postValue(searchRepository.getChannelsNow(currentSever.getId()));
                } else {
                    filterChannelList.postValue(
                            searchRepository.getChannelsNow(currentSever.getId())
                                    .stream()
                                    .filter(channel -> channel.getName().contains(searchText)
                                            || channel.getHandle().contains(searchText)
                                            || channel.getDescription().contains(searchText)
                                    )
                                    .collect(Collectors.toList())
                    );
                }
            }
        }, 500);
    }
}
