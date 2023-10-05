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
        if(userDao.getUserFromServerWithUsername(serverId, username) == null) {
            userDao.insertAll(new UserEntity(serverId, username, password, "tung", "Guest", 0xFFFFFFFF));
            ircService.login(username, username);
        }
        else {
            ircService.login(username, username);
        }
        return userDao.getUserFromServerWithUsername(serverId, username);
    }
}
