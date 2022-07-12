package com.GenSpark.BugTracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogInEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String email;
    private String password;
    private String name;
    private boolean admin;

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public boolean isAdmin() {return admin;}
    public void setAdmin(boolean admin) {this.admin = admin;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public LogInEntity() {
    }

    public LogInEntity(String email, String password, String name, boolean admin) {
        this.email = email;
        this.password = password;
        this.admin = admin;
        this.name = name;
    }


}
