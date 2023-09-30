package com.tungngt.dev.data.repository.impl;

import com.tungngt.dev.data.repository.AuthenticationRepository;
import com.tungngt.dev.database.dao.UserDao;
import com.tungngt.dev.domain.UserEntity;
import com.tungngt.dev.network.service.IRCService;

public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    private IRCService ircService;
    private UserDao userDao;
    public AuthenticationRepositoryImpl(IRCService ircService, UserDao userDao) {
        this.ircService = ircService;
        this.userDao = userDao;
    }
    @Override
    public UserEntity login(Long serverId, String username, String password) {
        // TODO: implement login with password
        ircService.login(username, username);
        return userDao.getUserFromServerWithUsername(serverId, username);
    }
}
