package com.GenSpark.BugTracker.controller;

import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.entity.OrganizationEntity;
import com.GenSpark.BugTracker.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    @GetMapping("/organs")
    public List<OrganizationEntity> getOrgans(){return this.organizationService.getAllOrganization();}

    @GetMapping("/organ/{organId}")
    public OrganizationEntity getOrgan(@PathVariable int organId){return this.organizationService.getOrganizationById(organId);}
    @PostMapping("/organ")
    public OrganizationEntity addOrgan(@RequestBody OrganizationEntity organizationEntity){ return this.organizationService.addOrganization(organizationEntity);}

    @PutMapping("/organ")
    public OrganizationEntity updateOrgan(@RequestBody OrganizationEntity organizationEntity){return this.organizationService.updateOrganization(organizationEntity);}

    @DeleteMapping("/organ/{organId}")
    public String deleteOrgan(@PathVariable int organId){return this.organizationService.deleteOrganization(organId);}

    // Mappigns For CRUD bugs
    @PostMapping("/organ/bug/{organId}")
    public String addBug(@PathVariable int organId, @RequestBody BugsEntity bugsEntity){return this.organizationService.addBug(organId, bugsEntity);}

    @PutMapping("/organ/bug/{organId}")
    public String updateBug(@PathVariable int organId, @RequestBody BugsEntity bugsEntity){return this.organizationService.updateBug(organId, bugsEntity);}

    @GetMapping("/organ/bugs/{organId}")
    public List<BugsEntity> getAllBugs(@PathVariable int organId){return this.organizationService.getAllBugs(organId);}
}
