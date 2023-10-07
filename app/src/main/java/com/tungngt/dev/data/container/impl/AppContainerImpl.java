package com.tungngt.dev.data.container.impl;

import android.app.Application;

import androidx.room.Room;

import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.data.repository.AuthenticationRepository;
import com.tungngt.dev.data.repository.ChatRepository;
import com.tungngt.dev.data.repository.ChatSettingRepository;
import com.tungngt.dev.data.repository.MainRepository;
import com.tungngt.dev.data.repository.SearchRepository;
import com.tungngt.dev.data.repository.ServerRepository;
import com.tungngt.dev.data.repository.impl.AuthenticationRepositoryImpl;
import com.tungngt.dev.data.repository.impl.ChatRepositoryImpl;
import com.tungngt.dev.data.repository.impl.ChatSettingRepositoryImpl;
import com.tungngt.dev.data.repository.impl.MainRepositoryImpl;
import com.tungngt.dev.data.repository.impl.SearchRepositoryImpl;
import com.tungngt.dev.data.repository.impl.ServerRepositoryImpl;
import com.tungngt.dev.database.AppDatabase;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.network.service.IRCService;
import com.tungngt.dev.network.service.impl.IRCServiceImpl;
import com.tungngt.dev.viewmodel.factory.AuthenticationViewModelFactory;
import com.tungngt.dev.viewmodel.factory.ChatSettingViewModelFactory;
import com.tungngt.dev.viewmodel.factory.ChatViewModelFactory;
import com.tungngt.dev.viewmodel.factory.MainViewModelFactory;
import com.tungngt.dev.viewmodel.factory.SearchViewModelFactory;
import com.tungngt.dev.viewmodel.factory.ServerListViewModelFactory;

import java.net.DatagramSocket;

public class AppContainerImpl implements AppContainer {
    private final ChatRepository chatRepository;
    private final ServerRepository serverRepository;
    private final AuthenticationRepository authenticationRepository;
    private final MainRepository mainRepository;
    private final SearchRepository searchRepository;
    private final ChatSettingRepository chatSettingRepository;

    private final AppDatabase appDatabase;
    private final Application application;
    private final IRCService ircService;

    private final ChatViewModelFactory chatViewModelFactory;
    private final ServerListViewModelFactory serverListViewModelFactory;
    private final AuthenticationViewModelFactory authenticationViewModelFactory;
    private final MainViewModelFactory mainViewModelFactory;
    private final SearchViewModelFactory searchViewModelFactory;
    private final ChatSettingViewModelFactory chatSettingViewModelFactory;

    private UserEntity loggedInUser;
    private ChannelEntity currentChannel;
    private ServerEntity currentServer;

    public AppContainerImpl(Application application) {
        this.application = application;
        ircService = IRCServiceImpl.getInstance();
        appDatabase = Room.databaseBuilder(
                application,
                AppDatabase.class, "timber-db"
        ).build();

        serverRepository = new ServerRepositoryImpl(
                ircService,
                appDatabase.getServerDao()
        );
        serverListViewModelFactory = new ServerListViewModelFactory(serverRepository);

        chatRepository = new ChatRepositoryImpl(
                ircService,
                getAppDatabase().getChatDao(),
                getAppDatabase().getUserDao()
        );
        chatViewModelFactory = new ChatViewModelFactory(chatRepository);

        authenticationRepository = new AuthenticationRepositoryImpl(
                ircService,
                appDatabase.getUserDao()
        );
        authenticationViewModelFactory = new AuthenticationViewModelFactory(
                authenticationRepository
        );

        mainRepository = new MainRepositoryImpl(ircService, getAppDatabase().getChannelDao());
        mainViewModelFactory = new MainViewModelFactory(mainRepository);

        searchRepository = new SearchRepositoryImpl(
                ircService,
                appDatabase.getServerDao(),
                appDatabase.getChannelDao()
        );
        searchViewModelFactory = new SearchViewModelFactory(searchRepository);
        chatSettingRepository = new ChatSettingRepositoryImpl(ircService);
        chatSettingViewModelFactory = new ChatSettingViewModelFactory(chatSettingRepository);
    }

    @Override
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    @Override
    public ChatRepository getChatRepository() {
        return chatRepository;
    }
    @Override
    public ServerRepository getServerRepository() {
        return serverRepository;
    }

    @Override
    public MainRepository getMainRepository() {
        return mainRepository;
    }

    @Override
    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    @Override
    public ChatSettingRepository getChatSettingRepository() {
        return chatSettingRepository;
    }

    @Override
    public SearchRepository getSearchRepository() {
        return searchRepository;
    }

    @Override
    public ChatViewModelFactory getChatViewModelFactory() {
        return chatViewModelFactory;
    }

    @Override
    public ServerListViewModelFactory getServerListViewModelFactory() {
        return serverListViewModelFactory;
    }

    @Override
    public AuthenticationViewModelFactory getAuthenticationViewModelFactory() {
        return authenticationViewModelFactory;
    }

    @Override
    public SearchViewModelFactory getSearchViewModelFactory() {
        return searchViewModelFactory;
    }

    @Override
    public MainViewModelFactory getMainViewModelFactory() {
        return mainViewModelFactory;
    }

    @Override
    public ChatSettingViewModelFactory getChatSettingViewModelFactory() {
        return chatSettingViewModelFactory;
    }

    @Override
    public UserEntity getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void setLoggedInUser(UserEntity user) {
        this.loggedInUser = user;
    }

    @Override
    public ChannelEntity getCurrentChannel() {
        return currentChannel;
    }

    @Override
    public void setCurrentChannel(ChannelEntity channel) {
        this.currentChannel = channel;
    }

    @Override
    public ServerEntity getCurrentServer() {
        return currentServer;
    }

    @Override
    public void setCurrentServer(ServerEntity server) {
        this.currentServer = server;
    }


}
