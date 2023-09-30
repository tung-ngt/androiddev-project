package com.tungngt.dev.viewmodel.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.tungngt.dev.data.repository.AuthenticationRepository;
import com.tungngt.dev.viewmodel.AuthenticationViewModel;

public class AuthenticationViewModelFactory implements ViewModelProvider.Factory {

    private final AuthenticationRepository authenticationRepository;

    public AuthenticationViewModelFactory(AuthenticationRepository authenticationRepository) {
         this.authenticationRepository = authenticationRepository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AuthenticationViewModel(authenticationRepository);
    }
}
