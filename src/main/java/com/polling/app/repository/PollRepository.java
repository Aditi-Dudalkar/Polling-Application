package com.polling.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polling.app.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
    // You can define custom query methods here if needed
}

