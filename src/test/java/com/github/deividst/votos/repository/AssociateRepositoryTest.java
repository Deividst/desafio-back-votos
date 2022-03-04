package com.github.deividst.votos.repository;

import com.github.deividst.votos.model.Associate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ActiveProfiles("test")
class AssociateRepositoryTest {

    @Autowired
    AssociateRepository associateRepository;

    @Test
    void givenFindByCpf_whenCpfExist_returnAssociate() {
        Associate associate = buildAssociate();
        associateRepository.save(associate);

        Associate savedAssociate = associateRepository.findByCpf(associate.getCpf());

        Assertions.assertEquals(savedAssociate.getCpf(), associate.getCpf());
    }

    @Test
    void givenFindByCpf_whenCpfNotExist_returnNull() {
        Associate savedAssociate = associateRepository.findByCpf(any());

        Assertions.assertNull(savedAssociate);
    }

    private Associate buildAssociate() {
        return Associate.builder()
                .registerDate(LocalDateTime.now())
                .cpf("36572935078")
                .name("test")
                .build();
    }

}