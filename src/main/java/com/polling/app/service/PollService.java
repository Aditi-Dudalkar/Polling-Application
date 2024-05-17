package com.polling.app.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polling.app.model.Poll;
import com.polling.app.model.PollResultDTO;
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

    public List<PollResultDTO> getPollResults() {
        List<Poll> polls = pollRepository.findAll();
        return polls.stream().map(poll -> {
            PollResultDTO resultDTO = new PollResultDTO();
            resultDTO.setPollId(poll.getId());
            resultDTO.setQuestion(poll.getQuestion());

            Map<String, Long> optionVoteCounts = poll.getVotes().stream()
                    .collect(Collectors.groupingBy(vote -> vote.getSelectedOption(), Collectors.counting()));

            long totalVotes = poll.getVotes().size();
            Map<String, Double> optionPercentages = new HashMap<>();

            for (String option : poll.getOptions()) {
                long count = optionVoteCounts.getOrDefault(option, 0L);
                double percentage = (totalVotes == 0) ? 0 : (double) count / totalVotes * 100;
                optionPercentages.put(option, percentage);
            }

            resultDTO.setOptionPercentages(optionPercentages);
            return resultDTO;
        }).collect(Collectors.toList());
    }

}
