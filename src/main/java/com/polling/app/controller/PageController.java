package com.polling.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.polling.app.model.Poll;
import com.polling.app.service.PollService;


@Controller
public class PageController {

    @Autowired
    private PollService pollService; 

    @GetMapping("/create-poll")
    public String showCreatePollPage() {
        return "create-poll"; // Return the name of the Thymeleaf template without the ".html" extension
    }

    // @GetMapping("/vote")
    // public String showVotePage() {
    //     return "vote"; // Return the name of the Thymeleaf template without the ".html" extension
    // }

    @GetMapping("/vote")
    public String showVotePage(Model model) {
        // Fetch all polls with their options from the database
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls); // Add the polls list to the model
        return "vote"; // Return the name of the Thymeleaf template without the ".html" extension
    }

    @GetMapping("/polls")
    public String getAllPolls(Model model) {
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls);
        return "polls"; // This corresponds to the name of your Thymeleaf template (e.g., "polls.html")
    }

}

