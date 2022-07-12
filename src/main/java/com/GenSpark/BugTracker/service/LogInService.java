package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.LogInEntity;

public interface LogInService {
    LogInEntity login(LogInEntity logInE);
    LogInEntity createUser(LogInEntity logIn);
}
