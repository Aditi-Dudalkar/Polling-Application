package com.polling.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polling.app.model.Poll;
import com.polling.app.repository.PollRepository;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getPollsBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return pollRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public Poll getPollById(Long pollId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPollById'");
    }

}
