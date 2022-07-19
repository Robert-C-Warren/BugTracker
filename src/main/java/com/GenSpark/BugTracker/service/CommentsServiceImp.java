package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.CommentsEntity;
import com.GenSpark.BugTracker.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImp implements CommentsService {
    @Autowired
    CommentsRepository commentsRepository;

    @Override
    public String deleteComment(int id) {
        this.commentsRepository.deleteById(id);
        return "Deleted Successfully";
    }

    @Override
    public CommentsEntity addComment(CommentsEntity commentsEntity) {
        return this.commentsRepository.save(commentsEntity);
    }
}
