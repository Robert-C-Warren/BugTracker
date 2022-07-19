package com.GenSpark.BugTracker.service;


import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.entity.CommentsEntity;

import java.util.List;

public interface BugService {

    List<BugsEntity> getAllBugs();
    BugsEntity getBugById(int bugId);
    BugsEntity addBug(BugsEntity bug);
    BugsEntity updateBug(BugsEntity bug);
    String deleteBug(int bugId);

    String addComment(int bugId, CommentsEntity commentsEntity);

    String deleteComment(int bugId, int commentId);

}
