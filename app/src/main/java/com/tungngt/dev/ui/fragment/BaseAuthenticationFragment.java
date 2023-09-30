package com.tungngt.dev.ui.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.ui.activity.AuthenticationActivity;
import com.tungngt.dev.viewmodel.AuthenticationViewModel;

public class BaseAuthenticationFragment extends Fragment {

    protected AuthenticationViewModel getAuthenticationViewModel() {
        AuthenticationActivity authenticationActivity = ((AuthenticationActivity) getActivity());
        return authenticationActivity.getAuthenticationViewModel();
    }

    protected AppContainer getAppContainer() {
        AuthenticationActivity authenticationActivity = ((AuthenticationActivity) getActivity());
        return authenticationActivity.getAppContainer();
    }
}
