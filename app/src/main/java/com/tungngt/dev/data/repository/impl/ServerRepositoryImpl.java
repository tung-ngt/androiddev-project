package com.tungngt.dev.data.repository.impl;

import androidx.lifecycle.LiveData;

import com.tungngt.dev.data.repository.ServerRepository;
import com.tungngt.dev.database.dao.ServerDao;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.network.service.IRCService;

import java.util.List;

public class ServerRepositoryImpl implements ServerRepository {
    private ServerDao serverDao;
    private IRCService ircService;

    public  ServerRepositoryImpl(IRCService ircService, ServerDao serverDao) {
        this.ircService = ircService;
        this.serverDao = serverDao;
    }
    @Override
    public LiveData<List<ServerEntity>> loadServers() {
        return serverDao.getAll();
    }

    @Override
    public void connectToServer(ServerEntity server) {
        ircService.connectServer(server.getUrl(), server.getPort());
    }

    @Override
    public void addServer(ServerEntity server) {
        serverDao.insertAll(server);
    }
}
