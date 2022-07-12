package com.GenSpark.BugTracker.entity;

import javax.persistence.*;

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

    public BugsEntity() {
    }

    public BugsEntity(boolean isPublished, String bugStatus, String bugDesc, String assignedTo, String bugName, String bugUrgency) {
        this.isPublished = isPublished;
        this.bugStatus = bugStatus;
        this.bugDesc = bugDesc;
        this.assignedTo = assignedTo;
        this.bugName = bugName;
        this.bugUrgency = bugUrgency;
    }
}
