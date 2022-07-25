package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.entity.CommentsEntity;
import com.GenSpark.BugTracker.entity.OrganizationEntity;

import java.util.List;

public interface OrganizationService {
    List<OrganizationEntity> getAllOrganization();

    OrganizationEntity getOrganizationById(int organId);
    OrganizationEntity addOrganization(OrganizationEntity organizationEntity);
    OrganizationEntity updateOrganization(OrganizationEntity organizationEntity);
    String deleteOrganization(int organId);
    String addBug(int bugId, BugsEntity bugsEntity);
    String updateBug(int bugId, BugsEntity bugsEntity);

    List<BugsEntity> getAllBugs(int organId);
}
