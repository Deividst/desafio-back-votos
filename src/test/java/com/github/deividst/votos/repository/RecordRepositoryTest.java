package com.github.deividst.votos.repository;

import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.model.Record;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class RecordRepositoryTest {

    @Autowired
    RecordRepository recordRepository;

    @Test
    void givenSavedRecord_shouldUpdateStatus() {
        Record record = recordRepository.save(buildRecord());

        recordRepository.updateStatus(RecordStatusEnum.IN_PROGRESS, record.getId());

        Optional<Record> recordSaved = recordRepository.findById(record.getId());

        Assertions.assertEquals(RecordStatusEnum.IN_PROGRESS, recordSaved.get().getStatus());
    }

    private Record buildRecord() {
        return Record.builder()
                .subject("test")
                .description("test")
                .status(RecordStatusEnum.IN_PROGRESS)
                .registerDate(LocalDateTime.now())
                .build();
    }

}