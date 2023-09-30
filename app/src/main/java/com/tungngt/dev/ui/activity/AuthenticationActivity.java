package com.tungngt.dev.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.MyApplication;
import com.tungngt.dev.data.container.AppContainer;
import com.tungngt.dev.databinding.ActivityAuthenticationBinding;
import com.tungngt.dev.domain.ServerEntity;
import com.tungngt.dev.viewmodel.AuthenticationViewModel;

public class AuthenticationActivity extends AppCompatActivity {
    private static final String TAG = "AuthenticationActivity";
    private ActivityAuthenticationBinding activityAuthenticationBinding;
    private AppContainer appContainer;
    private AuthenticationViewModel authenticationViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAuthenticationBinding = ActivityAuthenticationBinding.inflate(getLayoutInflater());
        setContentView(activityAuthenticationBinding.getRoot());

        appContainer = ((MyApplication) getApplication()).appContainer;
        authenticationViewModel = new ViewModelProvider(
                this, appContainer.getAuthenticationViewModelFactory()
        ).get(AuthenticationViewModel.class);

        // Process intent when clicked on a server
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ServerEntity server = (ServerEntity) extras.getSerializable("server");

            if (server != null) {
                activityAuthenticationBinding.setServer(server);

            }
        }

        Log.i(TAG, "onCreate: appcontainer " + appContainer);
        Log.i(TAG, "onCreate: viewmodel " + authenticationViewModel);
    }

    public AuthenticationViewModel getAuthenticationViewModel() {
        return authenticationViewModel;
    }

    public AppContainer getAppContainer() {
        return appContainer;
    }
}
