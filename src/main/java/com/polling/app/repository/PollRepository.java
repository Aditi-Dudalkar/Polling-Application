package com.polling.app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polling.app.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    List<Poll> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}

