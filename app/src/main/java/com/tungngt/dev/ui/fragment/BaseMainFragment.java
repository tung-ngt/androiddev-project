package com.tungngt.dev.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.ui.activity.MainActivity;
import com.tungngt.dev.viewmodel.MainViewModel;

public class BaseMainFragment extends Fragment {
    protected AppContainer getAppContainer() {
        MainActivity mainActivity = (MainActivity) getActivity();
        return mainActivity.getAppContainer();
    }

    protected MainViewModel getMainViewModel() {
        MainActivity mainActivity = (MainActivity) getActivity();
        return mainActivity.getMainViewModel();
    }
}
