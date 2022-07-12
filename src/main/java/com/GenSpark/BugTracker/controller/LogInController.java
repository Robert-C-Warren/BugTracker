package com.GenSpark.BugTracker.controller;

import com.GenSpark.BugTracker.entity.LogInEntity;
import com.GenSpark.BugTracker.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LogInController {
    @Autowired
    LogInService logInService;
    @PostMapping("/userregister")
    public LogInEntity createUser(@RequestBody LogInEntity logInE){return this.logInService.createUser(logInE);}
}
