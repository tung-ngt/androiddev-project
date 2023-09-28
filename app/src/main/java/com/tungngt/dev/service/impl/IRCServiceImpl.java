package com.tungngt.dev.service.impl;

import android.util.Log;

import com.tungngt.dev.service.IRCService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

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
    private Thread processSendMessageThread;
    private Boolean isConnected;

    private final Queue<String> sendQueue = new LinkedList<>();


    @Override
    public void login(String username, String realName) {
        addToSendQueue("NICK " + username);
        addToSendQueue("USER " + username + " 8 * : " + realName);
    }

    @Override
    public void sendMessage(String message, String receiver) {
        addToSendQueue("PRIVMSG " + receiver + " :" + message);
    }

    @Override
    public void connectServer(String url, Integer port) {

        new Thread(() -> {
            try {
                Log.i(TAG, "connectServer: hi");
                socket = new Socket(url, port);
                Log.i(TAG, "connectServer: hi");

                writer = new BufferedWriter(new PrintWriter(socket.getOutputStream()));
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception e) {
                Log.i(TAG, "connectServer: "  + e);
            }


            isConnected = true;
            messageListenerThread = new Thread(this::listenForMessage);
            messageListenerThread.start();

            processSendMessageThread = new Thread(this::processSendQueue);
            processSendMessageThread.start();
//
//            login("tungnguyen123", "Tung");
//            joinChannel("#usth");
//            sendMessage("test message", "#usth");

        }).start();
    }

    @Override
    public void leaveServer() {
        try {
            writer.write("disconnect");
            Log.i(TAG, "Disconnect from server");
            writer.flush();
            isConnected = false;
            sendQueue.clear();
            writer.close();
            reader.close();
            socket.close();
            writer = null;
            reader = null;
            socket = null;
            messageListenerThread = null;
        } catch (Exception e) {
            Log.i(TAG, "leaveServer: " + e);
        }
    }

    @Override
    public void joinChannel(String channelHandle) {
        addToSendQueue("JOIN " + channelHandle);
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
            while (isConnected) {
                if ((line = reader.readLine()) == null)
                    break;

                Log.i(TAG, "listenForMessage: " + line);

                if (line.contains("PING")) {
                    writeLine("PONG " + line.substring(5));
                    continue;
                }

                String[] parts = line.split(" ");
                String sendFrom = parts[0];
                String command = parts[1];

                if (command.equals("PRIVMSG")) {
                    String sender = sendFrom.split("!~")[0].substring(1);
                    String receiver = parts[2];
                    int secondColonIndex = line.indexOf(":", line.indexOf(":") + 1);
                    String message = line.substring( secondColonIndex + 1);;
                    onReceivedMessageListener.onReceivedMessage(sender, receiver, message);
                }

            }
        } catch (Exception e) {
            Log.i(TAG, "leaveServer: " + e);

        }
    }

    private void addToSendQueue(String line) {
        sendQueue.add(line);
    }

    private void processSendQueue() {
        try {
            while (isConnected) {
                if (sendQueue.isEmpty()) {
                   continue;
                }

                String line = sendQueue.remove();

                writeLine(line);
            }
        } catch (Exception e) {
            Log.i(TAG, "leaveServer: " + e);

        }
    }

    private void writeLine(String line) {
        try {
            writer.write(line + "\r\n");
            Log.i(TAG, "writeLine: " + line);
            writer.flush();
        } catch (IOException e) {
            Log.i(TAG, "leaveServer: " + e);

        }
    }
}
