package com.polling.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.polling.app.model.Poll;
import com.polling.app.service.PollService;

@RestController
@RequestMapping("/api")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/polls")
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll) {
        Poll createdPoll = pollService.createPoll(poll);
        return ResponseEntity.ok(createdPoll);
    }

    @GetMapping("/polls")
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls = pollService.getAllPolls();
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/between")
    public ResponseEntity<List<Poll>> getPollsBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Poll> polls = pollService.getPollsBetweenDates(startDate, endDate);
        return ResponseEntity.ok(polls);
    }
    
}

