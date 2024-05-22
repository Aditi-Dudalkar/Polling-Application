package com.polling.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.polling.app.model.Poll;
import com.polling.app.model.PollResultDTO;
import com.polling.app.service.PollService;


@Controller
public class PageController {

    @Autowired
    private PollService pollService; 

    @GetMapping("/create-poll")
    public String showCreatePollPage() {
        return "create-poll"; 
    }

    @GetMapping("/vote")
    public String showVotePage(Model model) {
        // Fetch all polls with their options from the database
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls); 
        return "vote"; 
    }

    @GetMapping("/polls")
    public String getAllPolls(Model model) {
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls);
        return "polls";
    }

    @GetMapping("/poll-results-page")
    public String getPollResultsPage(Model model) {
            List<PollResultDTO> pollResults = pollService.getPollResults();
            model.addAttribute("pollResults", pollResults);
            return "poll-results";
    }
}

