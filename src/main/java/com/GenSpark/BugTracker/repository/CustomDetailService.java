package com.GenSpark.BugTracker.repository;

import com.GenSpark.BugTracker.entity.LogInEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomDetailService implements UserDetailsService {
    @Autowired
    private LogInRepository logInRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LogInEntity logIn = logInRepository.findByEmail(username);
        if (logIn == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new LoginDetails(logIn);
    }
}
