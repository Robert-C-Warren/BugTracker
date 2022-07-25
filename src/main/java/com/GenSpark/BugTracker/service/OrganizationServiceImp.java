package com.GenSpark.BugTracker.service;

import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.entity.OrganizationEntity;
import com.GenSpark.BugTracker.repository.BugRepository;
import com.GenSpark.BugTracker.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImp implements OrganizationService{

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private BugService bugService;
    @Autowired
    private CommentsService commentsService;


    @Override
    public List<OrganizationEntity> getAllOrganization() {
        return this.organizationRepository.findAll();
    }

    @Override
    public OrganizationEntity getOrganizationById(int organId) {
        Optional<OrganizationEntity> b = this.organizationRepository.findById(organId);
        OrganizationEntity organizationEntity = null;
        if(b.isPresent()){
            organizationEntity = b.get();
        }
        else
            throw new RuntimeException("ERROR! Course not found for id ::");
        return organizationEntity;
    }

    @Override
    public OrganizationEntity addOrganization(OrganizationEntity organizationEntity) {
        return this.organizationRepository.save(organizationEntity);
    }

    @Override
    public OrganizationEntity updateOrganization(OrganizationEntity organizationEntity) {
        return this.organizationRepository.save(organizationEntity);
    }

    @Override
    public String deleteOrganization(int organId) {
        this.organizationRepository.deleteById(organId);
        return "Deleted Successfully";
    }

    @Override
    public String addBug(int organId, BugsEntity bugsEntity) {
        OrganizationEntity organizationEntity = this.getOrganizationById(organId);
        organizationEntity.getOrganizationBug().add(bugsEntity);
        this.organizationRepository.save(organizationEntity);
        return "Bug added";
    }

    @Override
    public List<BugsEntity> getAllBugs(int organId) {
        return this.getOrganizationById(organId).getOrganizationBug();
    }

    @Override
    public String updateBug(int organId, BugsEntity bugsEntity) {
        OrganizationEntity organizationEntity = this.getOrganizationById(organId);
        organizationEntity.getOrganizationBug().add(bugsEntity);
        this.organizationRepository.save(organizationEntity);
        return "Bug Updated";
    }


}
