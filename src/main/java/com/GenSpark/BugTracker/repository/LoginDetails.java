package com.GenSpark.BugTracker.repository;

import com.GenSpark.BugTracker.entity.LogInEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

public class LoginDetails implements UserDetails {

    private LogInEntity logIn;

    public  LoginDetails(LogInEntity logIn){
        super();
        this.logIn = logIn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(logIn.getRole()));
    }

    @Override
    public String getPassword() {
        return logIn.getPassword();
    }

    @Override
    public String getUsername() {return logIn.getEmail();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
