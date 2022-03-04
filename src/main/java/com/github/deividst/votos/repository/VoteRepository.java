package com.github.deividst.votos.repository;

import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Query(value = "SELECT v FROM TB_VOTE v WHERE :now > v.record.session.finalDate AND v.record.status = :statusEnum")
    List<Vote> findCompletedVoting(@Param("now") LocalDateTime now, @Param("statusEnum") RecordStatusEnum statusEnum);

}
