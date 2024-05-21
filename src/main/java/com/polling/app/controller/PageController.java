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
        System.out.println("Hello /vote");
        // Fetch all polls with their options from the database
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls); // Add the polls list to the model
        return "vote"; 
    }

    @GetMapping("/polls")
    public String getAllPolls(Model model) {
        System.out.println("Hello /polls");
        List<Poll> polls = pollService.getAllPolls();
        model.addAttribute("polls", polls);
        return "polls";
    }

    @GetMapping("/poll-results-page")
    public String getPollResultsPage(Model model) {
        System.out.println("Hello /page/poll-results-page");
        try {
            System.out.println("Inside try");
            List<PollResultDTO> pollResults = pollService.getPollResults();
            System.out.println("pollResults: " + pollResults);
            pollResults.forEach(pr -> {
                System.out.println("Question: " + pr.getQuestion());
                pr.getOptionPercentages().forEach((key, value) -> {
                    System.out.println("Option: " + key + ", Value: " + value);
                });
            });
            model.addAttribute("pollResults", pollResults);
            return "poll-results";
        } catch (Exception e) {
            System.out.println("Inside catch: An error happened during template parsing");
            e.printStackTrace();
            return "error";
        }
    }
}

