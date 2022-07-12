package com.GenSpark.BugTracker.service;


import com.GenSpark.BugTracker.entity.LogInEntity;
import com.GenSpark.BugTracker.repository.LogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImp implements LogInService {
    @Autowired
    LogInRepository logInRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public LogInEntity createUser(LogInEntity logIn) {
        logIn.setPassword(passwordEncoder.encode(logIn.getPassword()));
        return this.logInRepository.save(logIn);
    }
}
