package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.CommentsEntity;

public interface CommentsService {
    String deleteComment(int id);
    CommentsEntity addComment(CommentsEntity commentsEntity);
    

}
