package com.tungngt.dev.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.tungngt.dev.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private MutableLiveData<List<Message>> messages = new MutableLiveData<>(
            new ArrayList<>()
    );

    public MutableLiveData<List<Message>> getMessages() {
        return messages;
    }

    public void addMessage(Message message) {
        assert messages.getValue() != null;
        List<Message> newMessageList = new ArrayList<>(messages.getValue());
        newMessageList.add(message);
        messages.setValue(newMessageList);
    }

    public void postMessage(Message message) {
        assert messages.getValue() != null;
        List<Message> newMessageList = new ArrayList<>(messages.getValue());
        newMessageList.add(message);
        messages.postValue(newMessageList);
    }
}
