package com.tungngt.dev.service;

public interface IRCService {

    interface OnReceivedMessageListener {
        void onReceivedMessage(String sender, String receiver, String message, String time);
    };


    void login(String username, String realName);

    void sendMessage(String message, String receiver);

    void connectServer(String url, Integer port);

    void leaveServer();

    void joinChannel(String channelHandle);

    void leaveChannel();

    void setOnReceivedMessageListener(OnReceivedMessageListener onReceivedMessageListener);

//    List<Channel> findChannels();
//
//    List<User> showOnlineUsers(Channel channel);
//



}
