package com.GenSpark.BugTracker.service;


import com.GenSpark.BugTracker.entity.BugsEntity;

import java.util.List;

public interface BugService {

    List<BugsEntity> getAllBugs();
    BugsEntity addBug(BugsEntity bug);
    BugsEntity updateBug(BugsEntity bug);
    String deleteBug(int bugId);
}
