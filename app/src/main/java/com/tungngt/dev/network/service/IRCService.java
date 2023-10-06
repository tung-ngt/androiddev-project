package com.tungngt.dev.network.service;

import java.util.Date;
import java.util.List;

public interface IRCService {

    interface OnMessageReceivedListener {
        void onMessageReceived(String sender, String receiver, String message, Date time);
    }


    interface OnActiveUsersReceivedListener {
        void onActiveUsersReceived(List<String> nicks);
    }

    interface OnActiveUsersServerListener {
        void onActiveUsersServerReceived(List<String> nicks);
    }

    void login(String username, String realName);

    void sendMessage(String message, String receiver);

    void connectServer(String url, Integer port);

    void leaveServer();

    void joinChannel(String channelHandle);

    void leaveChannel();

    void addOnReceivedMessageListener(
            String channelHandle,
            String listenerType,
            OnMessageReceivedListener onReceivedMessageListener
    );

    void removeOnReceivedMessageListener(
            String channelHandle,
            String listenerType
    );

//    List<Channel> findChannels();
//
    void requestActiveUsers(String channelHandle, OnActiveUsersReceivedListener onActiveUsersReceivedListener);

}
