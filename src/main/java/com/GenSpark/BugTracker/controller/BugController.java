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

    @GetMapping("/bugs") // get all bugs request
    public List<BugsEntity> getBugs(){return this.bugService.getAllBugs();}

    @GetMapping("/bugs/{bugId}") // get bug by id request when passing an id with the request
    public BugsEntity getBug(@PathVariable int bugId){return this.bugService.getBugById(bugId);}
    @PostMapping("/bug")  // Add bug by request by passing a body
    public BugsEntity addBug(@RequestBody BugsEntity bug){ return this.bugService.addBug(bug);}

    @PutMapping("/bugs")  // update bug by id request when assigning an existing id in the body
    public BugsEntity updateBug(@RequestBody BugsEntity bug){return this.bugService.updateBug(bug);}

    @DeleteMapping("/bug/{bugId}") // delete bug by id request when passing an id with the request
    public String deleteBug(@PathVariable int bugId){return this.bugService.deleteBug(bugId);}

    @PostMapping("/comment/{bugId}") // Add comment to a bug request by passing id on path and a comment body
    public String addComment(@PathVariable int bugId, @RequestBody CommentsEntity commentsEntity){
        this.bugService.addComment(bugId, commentsEntity);
        return "Comment Added";
    }
    @DeleteMapping("/comment/delete/{bugId}/{commentId}") // delete comment from a bug request by passing id on path and a comment body
    public String deleteComment(@PathVariable int bugId, @PathVariable int commentId){
        this.bugService.deleteComment(bugId, commentId);
        return "Comment deleted";
    }

    @GetMapping("/sendMail/{toEmail}") // Send mail request by passing email to path
    public void sendEmail(@PathVariable String toEmail){
        emailSender.sendEmail(toEmail.trim() , "Bug Submission", "Thank you for your feedback \n" +
                                                                        "your feedback has been submitted \n" +
                                                                        "And we are looking into the issue as fast as possible ");
    }

}
