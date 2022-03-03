package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.VoteDto;
import com.github.deividst.votos.model.Associate;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.model.Vote;
import com.github.deividst.votos.repository.AssociateRepository;
import com.github.deividst.votos.repository.RecordRepository;
import com.github.deividst.votos.repository.VoteRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VoteService {

    private final AssociateRepository associateRepository;
    private final VoteRepository voteRepository;
    private final RecordRepository recordRepository;
    private final CPFIntegrationService cpfIntegrationService;

    public VoteService(AssociateRepository associateRepository, VoteRepository voteRepository,
                       RecordRepository recordRepository, CPFIntegrationService cpfIntegrationService) {
        this.associateRepository = associateRepository;
        this.voteRepository = voteRepository;
        this.recordRepository = recordRepository;
        this.cpfIntegrationService = cpfIntegrationService;
    }

    public void processVote(VoteDto voteDto) {
        Optional<Associate> associate = this.associateRepository.findById(voteDto.getAssociateId());

        if (associate.isEmpty())
            throw new EntityNotFoundException("Nenhum registro encontrato com o associateId informado.");

        cpfIntegrationService.checkCPF(associate.get().getCpf());

        Optional<Record> record = this.recordRepository.findById(voteDto.getRecordId());

        if (record.isEmpty())
            throw new EntityNotFoundException("Nenhum registro encontrato com o recordId informado.");

        this.voteRepository.save(Vote.builder()
                .voteType(voteDto.getVoteType())
                .registerDate(LocalDateTime.now())
                .record(record.get())
                .associate(associate.get())
                .build());
    }

}
