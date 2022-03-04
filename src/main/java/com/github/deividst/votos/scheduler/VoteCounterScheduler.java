package com.github.deividst.votos.scheduler;


import com.github.deividst.votos.stream.ResultVotePublisher;
import com.github.deividst.votos.dtos.ResultVoteDto;
import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.enums.TypeVoteEnum;
import com.github.deividst.votos.model.Vote;
import com.github.deividst.votos.service.RecordService;
import com.github.deividst.votos.service.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Slf4j
@Service
public class VoteCounterScheduler {

    private final VoteService voteService;
    private final ResultVotePublisher resultVotePublisher;
    private final RecordService recordService;

    public VoteCounterScheduler(VoteService voteService, ResultVotePublisher resultVotePublisher,
                                RecordService recordService) {
        this.voteService = voteService;
        this.resultVotePublisher = resultVotePublisher;
        this.recordService = recordService;
    }

    @Scheduled(fixedRate = 60000)
    public void processVoteCounter() {
        log.info("Iniciando processamento de apuração de votos {}", LocalDateTime.now());
        List<Vote> resultList = voteService.findCompletedVoting();

        Map<Long, List<Vote>> votePerRecord = resultList.stream()
                .collect(groupingBy(v -> v.getRecord().getId()));

        votePerRecord.forEach((id, votes) -> {
            Long countYes = votes.stream().filter(vote -> vote.getVoteType().equals(TypeVoteEnum.YES)).count();
            Long countNo = votes.stream().filter(vote -> vote.getVoteType().equals(TypeVoteEnum.NO)).count();

            ResultVoteDto result = ResultVoteDto.builder()
                    .countVoteNo(countNo)
                    .countVoteYes(countYes)
                    .totalVote(countNo + countYes)
                    .recordId(id)
                    .result(countYes > countNo ? RecordStatusEnum.APPROVED : RecordStatusEnum.DISAPPROVED)
                    .build();

            log.info("Resultado da apuração: {}", result.toString());

            this.recordService.updateStatus(result.getResult(), result.getRecordId());
            this.resultVotePublisher.publish(result);
        });
        log.info("Finalizado processamento de apuração de votos {}", LocalDateTime.now());
    }
}
