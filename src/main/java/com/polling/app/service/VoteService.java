package com.polling.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public List<Vote> vote(List<Vote> votes) {
        // Extract poll IDs from the list of votes
        List<Long> pollIds = votes.stream()
                                .map(vote -> vote.getPoll().getId())
                                .collect(Collectors.toList());

        // Fetch all the corresponding poll entities in a single database call
        List<Poll> polls = pollRepository.findAllById(pollIds);

        // Create a map of poll IDs to poll entities for easy lookup
        Map<Long, Poll> pollMap = polls.stream()
                                    .collect(Collectors.toMap(Poll::getId, Function.identity()));

        // Associate each vote with its respective poll entity and save all votes
        List<Vote> votesToSave = new ArrayList<>();
        for (Vote vote : votes) {
            Poll poll = pollMap.get(vote.getPoll().getId());
            if (poll == null) {
                throw new PollNotFoundException("Poll not found for ID: " + vote.getPoll().getId());
            }
            vote.setPoll(poll);
            votesToSave.add(vote);
        }
        return voteRepository.saveAll(votesToSave);
    }
}
