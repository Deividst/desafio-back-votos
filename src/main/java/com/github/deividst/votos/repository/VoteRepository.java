package com.github.deividst.votos.repository;

import com.github.deividst.votos.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
