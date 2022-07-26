package com.GenSpark.BugTracker.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class BugsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bugId;
    private  boolean isPublished;
    private String bugStatus;
    private String bugDesc;
    private String assignedTo;
    private String bugName;
    private String bugUrgency;
    //Relational Mapping. Creating a column called bug_id for every comment in a bug
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( name = "bug_id")
    private List<CommentsEntity> bugComments = new ArrayList<>();

    public int getBugId() {return bugId;}
    public void setBugId(int bugId) {this.bugId = bugId;}

    public boolean isPublished() {return isPublished;}
    public void setPublished(boolean published) {isPublished = published;}

    public String getBugStatus() {return bugStatus;}
    public void setBugStatus(String bugStatus) {this.bugStatus = bugStatus;}

    public String getBugDesc() {return bugDesc;}
    public void setBugDesc(String bugDesc) {this.bugDesc = bugDesc;}

    public String getAssignedTo() {return assignedTo;}
    public void setAssignedTo(String assignedTo) {this.assignedTo = assignedTo;}

    public String getBugName() {return bugName;}
    public void setBugName(String bugName) {this.bugName = bugName;}

    public String getBugUrgency() {return bugUrgency;}
    public void setBugUrgency(String bugUrgency) {this.bugUrgency = bugUrgency;}

    public List<CommentsEntity> getBugComments() {return bugComments;}
    public void setBugComments(List<CommentsEntity> bugComments) {this.bugComments = bugComments;}

    public BugsEntity() {
    }

    public BugsEntity(int bugId, boolean isPublished, String bugStatus, String bugDesc, String assignedTo, String bugName, String bugUrgency, List<CommentsEntity> bugComments) {
        this.bugId = bugId;
        this.isPublished = isPublished;
        this.bugStatus = bugStatus;
        this.bugDesc = bugDesc;
        this.assignedTo = assignedTo;
        this.bugName = bugName;
        this.bugUrgency = bugUrgency;
        this.bugComments = bugComments;
    }
/*
    public BugsEntity(int bugId, boolean isPublished, String bugStatus, String bugDesc, String assignedTo, String bugName, String bugUrgency) {
        this.bugId = bugId;
        this.isPublished = isPublished;
        this.bugStatus = bugStatus;
        this.bugDesc = bugDesc;
        this.assignedTo = assignedTo;
        this.bugName = bugName;
        this.bugUrgency = bugUrgency;
    }

 */
}
