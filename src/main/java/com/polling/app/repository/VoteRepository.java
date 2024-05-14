package com.polling.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.polling.app.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    // You can define custom query methods here if needed
}

