package com.GenSpark.BugTracker.repository;

import com.GenSpark.BugTracker.entity.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Integer> {
}
