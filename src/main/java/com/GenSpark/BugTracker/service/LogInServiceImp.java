package com.GenSpark.BugTracker.service;


import com.GenSpark.BugTracker.entity.LogInEntity;
import com.GenSpark.BugTracker.repository.LogInRepository;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogInServiceImp implements LogInService {
    @Autowired
    LogInRepository logInRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override // Add User
    public LogInEntity createUser(LogInEntity logIn) {
        logIn.setPassword(passwordEncoder.encode(logIn.getPassword()));
        return this.logInRepository.save(logIn);
    }

    @Override // Delete User
    public String deleteUser(long userid) {
         this.logInRepository.deleteById(userid);
         return "deleted successfully";
    }

    @Override // Retrieve All users
    public List<LogInEntity> getAllUsers() {
        return this.logInRepository.findAll();
    }

    @Override // Retrieve user by id
    public LogInEntity getUserById(long userId) {
        Optional<LogInEntity> u = this.logInRepository.findById(userId);
        LogInEntity user = null;
        if(u.isPresent()){
            user = u.get();
        } else {
            throw new RuntimeException("User not found for id :: " + userId);
        }

        return user;
    }

    @Override // Update user
    public LogInEntity updateUser(LogInEntity login) {
        LogInEntity oldLogin = getUserById(login.getUserId());
        login.setPassword(oldLogin.getPassword());
        return this.logInRepository.save(login);
    }
}
