package com.polling.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polling.app.model.Vote;
import com.polling.app.service.VoteService;

@RestController
@RequestMapping("/api")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping("/votes")
    public ResponseEntity<List<Vote>> vote(@RequestBody List<Vote> votes) {
        List<Vote> savedOptions = voteService.vote(votes);
        return ResponseEntity.ok().body(savedOptions);
    }

}

