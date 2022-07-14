package com.GenSpark.BugTracker.controller;

import com.GenSpark.BugTracker.configuration.JWTTokenHelper;
import com.GenSpark.BugTracker.entity.LogInEntity;
import com.GenSpark.BugTracker.request_response.AuthenticationRequest;
import com.GenSpark.BugTracker.request_response.LogInResponse;
import com.GenSpark.BugTracker.request_response.UserInfo;
import com.GenSpark.BugTracker.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@CrossOrigin(origins = "*")
public class LogInController {
    @Autowired
    LogInService logInService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {
      final  Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails logIn = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(logIn.getUsername());

        LogInResponse response = new LogInResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal user){
        LogInEntity logIn = (LogInEntity) userDetailsService.loadUserByUsername(user.getName());

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(logIn.getEmail());
        userInfo.setName(logIn.getName());
        userInfo.setRoles(logIn.getRole());

        return ResponseEntity.ok(userInfo);
    }
    @DeleteMapping("/user/{userId}")
    public String deleteBug(@PathVariable int userId){return this.logInService.deleteUser(userId);}


    @PostMapping("/userregister")
    public LogInEntity createUser(@RequestBody LogInEntity logInE){return this.logInService.createUser(logInE);}


}
