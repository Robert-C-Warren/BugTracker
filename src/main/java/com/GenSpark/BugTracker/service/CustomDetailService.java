package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.LogInEntity;
import com.GenSpark.BugTracker.repository.LogInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component // implements Spring Security UserDetailsService interface that has one method
public class CustomDetailService implements UserDetailsService {
    @Autowired
    private LogInRepository logInRepository;

    @Override // Override loadUserByUsername method from UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieving A LoginEntity Object by a get method findByEmail
        LogInEntity logIn = logInRepository.findByEmail(username);

        // If login is not found return user not found
        if (logIn == null){
            throw new UsernameNotFoundException("user not found");
        }

        return logIn;
    }
}
