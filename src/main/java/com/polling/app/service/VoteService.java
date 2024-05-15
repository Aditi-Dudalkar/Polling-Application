package com.polling.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polling.app.exception.PollNotFoundException;
import com.polling.app.model.Poll;
import com.polling.app.model.Vote;
import com.polling.app.repository.VoteRepository;
import com.polling.app.repository.PollRepository;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollRepository pollRepository;

    public void vote(Vote vote) {
        // Fetch the poll entity using pollId
        Poll poll = pollRepository.findById(vote.getPoll().getId()).orElse(null);
        if (poll == null) {
            // Handle case where poll with given ID is not found
            throw new PollNotFoundException("Poll not found for ID: " + vote.getPoll().getId());
        }
        // Set the poll attribute of the vote entity
        vote.setPoll(poll);
        // Save the vote entity
        voteRepository.save(vote);
    }

}
