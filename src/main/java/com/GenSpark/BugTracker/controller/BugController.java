package com.GenSpark.BugTracker.controller;

import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class BugController {
    @Autowired
    private BugService bugService;

    @GetMapping("/bugs")
    public List<BugsEntity> getBugs(){return this.bugService.getAllBugs();}

    @PostMapping("/bug")
    public BugsEntity addBug(@RequestBody BugsEntity bug){ return this.bugService.addBug(bug);}

    @PutMapping("/bug")
    public BugsEntity updateBug(@RequestBody BugsEntity bug){return this.bugService.updateBug(bug);}

    @DeleteMapping("/bug/{bugId}")
    public String deleteBug(@PathVariable int bugId){return this.bugService.deleteBug(bugId);}

}
