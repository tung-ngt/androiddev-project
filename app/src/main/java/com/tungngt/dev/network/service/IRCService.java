package com.tungngt.dev.network.service;

import java.util.Date;

public interface IRCService {

    interface OnMessageReceivedListener {
        void onMessageReceived(String sender, String receiver, String message, Date time);
    };


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
//    List<User> showOnlineUsers(Channel channel);
//

}
