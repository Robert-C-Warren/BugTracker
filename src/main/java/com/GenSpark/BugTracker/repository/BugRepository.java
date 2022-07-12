package com.GenSpark.BugTracker.repository;

import com.GenSpark.BugTracker.entity.BugsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends JpaRepository<BugsEntity, Integer> {
}
