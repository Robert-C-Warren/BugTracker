package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.LogInEntity;
import com.GenSpark.BugTracker.repository.LogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LogInServiceImp implements LogInService {
    @Autowired
    LogInRepository logInRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public LogInEntity login(LogInEntity logInE) {
        LogInEntity logIn = this.logInRepository.findOneByEmailAndPassword(logInE.getEmail(), logInE.getPassword());

        if( logIn == null){
            return new LogInEntity("failed","failed","failed",false);
        }
        else
            return logIn;
    }

    @Override
    public LogInEntity createUser(LogInEntity logIn) {
        logIn.setPassword(passwordEncoder.encode(logIn.getPassword()));
        return this.logInRepository.save(logIn);
    }
}
