package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.LogInEntity;


public interface LogInService {

    LogInEntity createUser(LogInEntity logIn);

    String deleteUser(long userid);
}
