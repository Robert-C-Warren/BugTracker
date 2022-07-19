package com.GenSpark.BugTracker.controller;

import com.GenSpark.BugTracker.entity.BugsEntity;
import com.GenSpark.BugTracker.entity.CommentsEntity;
import com.GenSpark.BugTracker.service.BugService;
import com.GenSpark.BugTracker.service.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BugController {

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private BugService bugService;

    @GetMapping("/bugs")
    public List<BugsEntity> getBugs(){return this.bugService.getAllBugs();}

    @GetMapping("/bugs/{bugId}")
    public BugsEntity getBug(@PathVariable int bugId){return this.bugService.getBugById(bugId);}
    @PostMapping("/bug")
    public BugsEntity addBug(@RequestBody BugsEntity bug){ return this.bugService.addBug(bug);}

    @PutMapping("/bugs")
    public BugsEntity updateBug(@RequestBody BugsEntity bug){return this.bugService.updateBug(bug);}

    @DeleteMapping("/bug/{bugId}")
    public String deleteBug(@PathVariable int bugId){return this.bugService.deleteBug(bugId);}

    @GetMapping("/user")
    public String helloUser(){
        return "user";
    }

    @GetMapping("/admin")
    public String helloAdmin(){
        return "admin";
    }

    @PostMapping("/comment/{bugId}")
    public String addComment(@PathVariable int bugId, @RequestBody CommentsEntity commentsEntity){
        this.bugService.addComment(bugId, commentsEntity);
        return "Comment Added";
    }
    @DeleteMapping("/comment/delete/{bugId}/{commentId}")
    public String deleteComment(@PathVariable int bugId, @PathVariable int commentId){
        this.bugService.deleteComment(bugId, commentId);
        return "Comment deleted";
    }


    @GetMapping("/sendMail/{toEmail}")
    public void sendEmail(@PathVariable String toEmail){
        emailSender.sendEmail(toEmail.trim() , "Bug Submission", "Thank you for your feedback \n" +
                                                                        "your feedback has been submitted \n" +
                                                                        "And we are looking into the issue as fast as possible ");
    }

}
