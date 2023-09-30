package com.tungngt.dev.data.repository;


import com.tungngt.dev.domain.UserEntity;

public interface AuthenticationRepository {
    UserEntity login(Long serverId, String username, String password);
}
