package com.tungngt.dev.network.service.impl;

import android.util.Log;

import com.tungngt.dev.network.service.IRCService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Date;

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

    private final Map<String, Map<String, OnMessageReceivedListener>> messageListenerMap = new HashMap<>();
    private OnActiveUsersReceivedListener onActiveUsersReceivedListener;

    private final List<String> activeUserBuffer = new ArrayList<>();

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
    public void addOnReceivedMessageListener(
            String channelHandle,
            String listenerType,
            OnMessageReceivedListener onReceivedMessageListener
    ) {
        if (messageListenerMap.containsKey(channelHandle)) {
            messageListenerMap.get(channelHandle).put(listenerType, onReceivedMessageListener);
            return;
        }

        Map<String, OnMessageReceivedListener> map = new HashMap<>();
        map.put(listenerType, onReceivedMessageListener);
        messageListenerMap.put(channelHandle, map);

    }

    @Override
    public void removeOnReceivedMessageListener(String channelHandle, String listenerType) {
        if (!messageListenerMap.containsKey(channelHandle)) return;

        messageListenerMap.get(channelHandle).remove(listenerType);
    }

    //
//    @Override
//    public List<Channel> findChannels() {
//        return null;
//    }
//


    @Override
    public void requestActiveUsers(String channelHandle, OnActiveUsersReceivedListener onActiveUsersReceivedListener) {
        addToSendQueue("NAMES "+channelHandle);
        this.onActiveUsersReceivedListener = onActiveUsersReceivedListener;
    }

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

                // Return names command
                if (command.equals("353")) {
                    int secondColonIndex = line.indexOf(":", line.indexOf(":") + 1);
                    String[] nicks = line.substring(secondColonIndex + 1).split(" ");
                    activeUserBuffer.addAll(Arrays.asList(nicks));
                }
                // finish names command
                if (command.equals("366")) {
                    finishActiveUsers();
                }

                if (command.equals("PRIVMSG")) {
                    String sender = sendFrom.split("!~")[0].substring(1);
                    String receiver = parts[2];
                    int secondColonIndex = line.indexOf(":", line.indexOf(":") + 1);
                    String message = line.substring(line.indexOf(":", line.indexOf(" ") + 3) + 1);
                    Date time = Calendar.getInstance().getTime();

                    new Thread(() -> {
                        processListenersThread(sender, receiver, message, time);
                    }).start();
                }

            }
        } catch (Exception e) {
            Log.i(TAG, "leaveServer: " + e);

        }
    }

    private void finishActiveUsers() {
        new Thread(() -> {
            if (onActiveUsersReceivedListener == null) {
                activeUserBuffer.clear();
                return;
            }

            onActiveUsersReceivedListener.onActiveUsersReceived(activeUserBuffer);
            activeUserBuffer.clear();
        }).start();
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

    private void processListenersThread(String sender, String receiver, String message, Date time) {
        if (!messageListenerMap.containsKey(receiver)) return;

        messageListenerMap.get(receiver).forEach((listenerType, listener) -> {
            listener.onMessageReceived(sender, receiver, message, time);
        });
    }
}
