package com.tungngt.dev.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.data.repository.ChatSettingRepository;
import com.tungngt.dev.domain.ChannelEntity;
import com.tungngt.dev.model.ActiveUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class ChatSettingViewModel extends ViewModel {
    private ChatSettingRepository chatSettingRepository;

    public ChatSettingViewModel(ChatSettingRepository chatSettingRepository) {
        this.chatSettingRepository = chatSettingRepository;
    }

    private MutableLiveData<List<ActiveUser>> activeUsers = new MutableLiveData<>(
            new ArrayList<>()
    );

    public LiveData<List<ActiveUser>> getActiveUsers() {
        return activeUsers;
    }

    private Timer timer = new Timer();
    public void requestActiveUser(ChannelEntity channel) {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                chatSettingRepository.requestActiveUsers(channel.getHandle(), nicks -> {
                    activeUsers.postValue(nicks.stream().map(ActiveUser::new).collect(Collectors.toList()));
                });
            }
        }, 0, 3000);
    }

    public void cancelRequestActiveUser() {
        timer.cancel();
    }
}
