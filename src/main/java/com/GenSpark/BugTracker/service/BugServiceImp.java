package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.entity.CommentsEntity;
import com.GenSpark.BugTracker.repository.BugRepository;
import com.GenSpark.BugTracker.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BugServiceImp implements BugService{

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<BugsEntity> getAllBugs() {
        return this.bugRepository.findAll();
    }

    @Override
    public BugsEntity getBugById(int bugId){
        Optional<BugsEntity> b = this.bugRepository.findById(bugId);
        BugsEntity bugsEntity = null;
        if(b.isPresent()){
            bugsEntity = b.get();
        }
        else
            throw new RuntimeException("ERROR! Course not found for id ::");
        return bugsEntity;
    }
    @Override
    public BugsEntity addBug(BugsEntity bug) {
        return this.bugRepository.save(bug);
    }

    @Override
    public BugsEntity updateBug(BugsEntity bug) {
        return this.bugRepository.save(bug);
    }

    @Override
    public String deleteBug(int bugId) {
        this.bugRepository.deleteById(bugId);

        return "Deleted Successfully";
    }

    @Override
    public String addComment(int bugId, CommentsEntity commentsEntity) {
        BugsEntity bugsEntity = this.getBugById(bugId);
        bugsEntity.getBugComments().add(commentsEntity);
        this.bugRepository.save(bugsEntity);
        return "Comment added";
    }

    @Override
    public String deleteComment(int bugId, int commentId) {
        BugsEntity bugsEntity = this.getBugById(bugId);
        bugsEntity.getBugComments().remove(commentsRepository.findById(commentId).get());
        this.bugRepository.save(bugsEntity);
        return "deleted Successfully";
    }
}
