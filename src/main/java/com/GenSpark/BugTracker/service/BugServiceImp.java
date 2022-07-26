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

    @Override //retrieve all bugs
    public List<BugsEntity> getAllBugs() {
        return this.bugRepository.findAll();
    }

    @Override // retrieve bug by id
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
    @Override // add bug
    public BugsEntity addBug(BugsEntity bug) {
        return this.bugRepository.save(bug);
    }

    @Override // update bug
    public BugsEntity updateBug(BugsEntity bug) {
        return this.bugRepository.save(bug);
    }

    @Override // delete bug
    public String deleteBug(int bugId) {
        this.bugRepository.deleteById(bugId);

        return "Deleted Successfully";
    }

    @Override // add a comment to a bug
    public String addComment(int bugId, CommentsEntity commentsEntity) {
        BugsEntity bugsEntity = this.getBugById(bugId);
        bugsEntity.getBugComments().add(commentsEntity);
        this.bugRepository.save(bugsEntity);
        return "Comment added";
    }

    @Override // delete a comment from a bug
    public String deleteComment(int bugId, int commentId) {
        BugsEntity bugsEntity = this.getBugById(bugId);
        bugsEntity.getBugComments().remove(commentsRepository.findById(commentId).get());
        this.bugRepository.save(bugsEntity);
        return "deleted Successfully";
    }
}
