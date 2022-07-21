package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.LogInEntity;

import java.util.List;


public interface LogInService {

    LogInEntity createUser(LogInEntity logIn);

    String deleteUser(long userid);

    List<LogInEntity> getAllUsers();

    LogInEntity getUserById(long userId);

    LogInEntity updateUser(LogInEntity login);
}
