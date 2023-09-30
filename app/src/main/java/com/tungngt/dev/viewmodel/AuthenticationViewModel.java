package com.tungngt.dev.viewmodel;

import androidx.lifecycle.ViewModel;

import com.tungngt.dev.data.repository.AuthenticationRepository;
import com.tungngt.dev.domain.UserEntity;

public class AuthenticationViewModel extends ViewModel {
    private AuthenticationRepository authenticationRepository;

    public AuthenticationViewModel(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    public UserEntity login(Long serverId, String username, String password) {
       return authenticationRepository.login(serverId, username, password);

    }

}
