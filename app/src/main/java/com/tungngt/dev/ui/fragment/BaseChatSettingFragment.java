package com.tungngt.dev.ui.fragment;

import androidx.fragment.app.Fragment;

import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.ui.activity.ChatSettingActivity;
import com.tungngt.dev.viewmodel.ChatSettingViewModel;

public class BaseChatSettingFragment extends Fragment {
    protected ChatSettingViewModel getChatSettingViewModel() {
        ChatSettingActivity chatSettingActivity = ((ChatSettingActivity) getActivity());
        return chatSettingActivity.getChatSettingViewModel();
    }

    protected AppContainer getAppContainer() {
        ChatSettingActivity chatSettingActivity = ((ChatSettingActivity) getActivity());
        return chatSettingActivity.getAppContainer();
    }
}
