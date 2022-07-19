package com.GenSpark.BugTracker.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class CommentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentsId;
    private String text;
    private String postedBy;
    private LocalDate postedAt =  LocalDate.now();


    public int getId() {return commentsId;}
    public void setId(int id) {this.commentsId = id;}

    public String getText() {return text;}
    public void setText(String text) {this.text = text;}

    public String getPostedBy() {return postedBy;}
    public void setPostedBy(String postedBy) {this.postedBy = postedBy;}

    public LocalDate getPostedAt() {return postedAt;}
    public void setPostedAt(LocalDate postedAt) {this.postedAt = postedAt;}

    public CommentsEntity() {
    }

    public CommentsEntity(int commentsId, String text, String postedBy, LocalDate postedAt) {
        this.commentsId = commentsId;
        this.text = text;
        this.postedBy = postedBy;
        this.postedAt = postedAt;

    }

}
