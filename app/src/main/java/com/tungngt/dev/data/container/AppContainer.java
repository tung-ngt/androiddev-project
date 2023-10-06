package com.tungngt.dev.data.container;

import com.tungngt.dev.data.repository.AuthenticationRepository;
import com.tungngt.dev.data.repository.ChatRepository;
import com.tungngt.dev.data.repository.ChatSettingRepository;
import com.tungngt.dev.data.repository.MainRepository;
import com.tungngt.dev.data.repository.SearchRepository;
import com.tungngt.dev.data.repository.ServerRepository;
import com.tungngt.dev.database.AppDatabase;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.viewmodel.factory.AuthenticationViewModelFactory;
import com.tungngt.dev.viewmodel.factory.ChatSettingViewModelFactory;
import com.tungngt.dev.viewmodel.factory.ChatViewModelFactory;
import com.tungngt.dev.viewmodel.factory.MainViewModelFactory;
import com.tungngt.dev.viewmodel.factory.SearchViewModelFactory;
import com.tungngt.dev.viewmodel.factory.ServerListViewModelFactory;

import java.net.DatagramSocket;

public interface AppContainer {

    ChatRepository getChatRepository();
    ServerRepository getServerRepository();
    AuthenticationRepository getAuthenticationRepository();
    MainRepository getMainRepository();
    SearchRepository getSearchRepository();
    ChatSettingRepository getChatSettingRepository();
    ChatViewModelFactory getChatViewModelFactory();
    ServerListViewModelFactory getServerListViewModelFactory();
    AuthenticationViewModelFactory getAuthenticationViewModelFactory();
    MainViewModelFactory getMainViewModelFactory();
    SearchViewModelFactory getSearchViewModelFactory();
    ChatSettingViewModelFactory getChatSettingViewModelFactory();


    UserEntity getLoggedInUser();
    void setLoggedInUser(UserEntity user);
    ChannelEntity getCurrentChannel();
    void setCurrentChannel(ChannelEntity channel);
    ServerEntity getCurrentServer();
    void setCurrentServer(ServerEntity server);

    AppDatabase getAppDatabase();
}
