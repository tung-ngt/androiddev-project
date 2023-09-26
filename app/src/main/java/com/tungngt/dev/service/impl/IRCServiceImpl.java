package com.tungngt.dev.service.impl;

import android.util.Log;

import com.tungngt.dev.service.IRCService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IRCServiceImpl implements IRCService {
    private static IRCServiceImpl instance;

    private IRCServiceImpl() {}

    public static IRCServiceImpl getInstance() {
        if (instance == null) {
            instance = new IRCServiceImpl();
        }

        return instance;
    }

    private static final String TAG = "IRCServiceImpl";
    private OnReceivedMessageListener onReceivedMessageListener;

    private Socket socket;
    private BufferedWriter writer;
    private BufferedReader reader;

    private Thread messageListenerThread;
    private Boolean isListening;


    @Override
    public void login(String username, String realName) {
        writeLine("NICK " + username);
        writeLine("USER " + username + " 8 * : " + realName);
        Log.i(TAG, "Login OK");
    }

    @Override
    public void sendMessage(String message, String receiver) {
        writeLine("PRIVMSG " + receiver + " :" + message);
        Log.i(TAG, "SEND nudes");
    }

    @Override
    public void connectServer(String url, Integer port) {
        new Thread(() -> {
            try {
                socket = new Socket(url, port);
                writer = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception e) {
                Log.i(TAG, "connectServer: "  + e.toString());
            }


            isListening = true;
            messageListenerThread = new Thread(() -> {
                listenForMessage();
            });
            messageListenerThread.start();

            login("tungngt", "Tung");
            joinChannel("#usth");
            sendMessage("test message", "#usth");
        }).start();
    }

    @Override
    public void leaveServer() {
        try {
            isListening = false;
            writer.close();
            reader.close();
            socket.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void joinChannel(String channelHandle) {
        writeLine("JOIN " + channelHandle);
    }

    @Override
    public void leaveChannel() {

    }

    @Override
    public void setOnReceivedMessageListener(OnReceivedMessageListener onReceivedMessageListener) {
        this.onReceivedMessageListener = onReceivedMessageListener;
    }
//
//    @Override
//    public List<Channel> findChannels() {
//        return null;
//    }
//
//    @Override
//    public List<User> showOnlineUsers(Channel channel) {
//        return null;
//    }

    private void listenForMessage() {
        String line;
        try {
            while (isListening) {
                if ((line = reader.readLine()) == null)
                    break;

                Log.i(TAG, "listenForMessage: " + line);
            }
        } catch (Exception e) {

        }
    }

    private void writeLine(String line) {
        try {
            Log.i(TAG, "writer: " + writer.toString());
            writer.write(line + "\r\n");
            writer.flush();
        } catch (IOException e) {

        }
    }
}
