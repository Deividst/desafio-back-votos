package com.github.deividst.votos.repository;

import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.enums.TypeVoteEnum;
import com.github.deividst.votos.model.Associate;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.model.Session;
import com.github.deividst.votos.model.Vote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class VoteRepositoryTest {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    AssociateRepository associateRepository;

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Test
    void givenVoteSaved_whenFindCompletedVoting(){

        Session session = sessionRepository.save(Session.builder()
                .initialDate(LocalDateTime.of(2022, 3,4,12,0,0))
                .finalDate(LocalDateTime.of(2022, 3,4,12,1,0))
                .build());

        Associate associate = associateRepository.save(Associate.builder()
                .cpf("36572935078")
                .name("test")
                .registerDate(LocalDateTime.now())
                .build());

        Record record = recordRepository.save(Record.builder()
                .status(RecordStatusEnum.APPROVED)
                .description("test")
                .session(session)
                .subject("test")
                .registerDate(LocalDateTime.now())
                .build());

        voteRepository.save(Vote.builder()
                .associate(associate)
                .record(record)
                .voteType(TypeVoteEnum.YES)
                .registerDate(LocalDateTime.now())
                .build());

        List<Vote> votes = voteRepository.findCompletedVoting(LocalDateTime.of(2022, 3,4,12,3,0), RecordStatusEnum.APPROVED);
        Assertions.assertEquals(1, votes.size());
    }
}