package com.tungngt.dev.data.repository;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.domain.ServerEntity;

import java.util.List;

public interface ServerRepository {

    LiveData<List<ServerEntity>> loadServers();

    void connectToServer(ServerEntity server);

    void addServer(ServerEntity server);

}
