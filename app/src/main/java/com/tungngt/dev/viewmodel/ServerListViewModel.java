package com.tungngt.dev.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.data.repository.ServerRepository;
import com.tungngt.dev.domain.ServerEntity;

import java.util.List;

public class ServerListViewModel extends ViewModel {
    private ServerRepository serverRepository;
    public ServerListViewModel(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }
    public LiveData<List<ServerEntity>> loadServers() {
        return serverRepository.loadServers();
    }

    public void connectToServer(ServerEntity server) {
        serverRepository.connectToServer(server);
    }

    public void addServer(String url, Integer port, String name, Integer color) {
        new Thread(() -> {
            serverRepository.addServer(
                    new ServerEntity(url, port, name, color)
            );
        }).start();
    }
}
