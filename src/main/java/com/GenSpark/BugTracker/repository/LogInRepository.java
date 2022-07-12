package com.GenSpark.BugTracker.repository;

import com.GenSpark.BugTracker.entity.LogInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInRepository extends JpaRepository<LogInEntity, Long> {
    LogInEntity findOneByEmailAndPassword(String email, String password);
}
