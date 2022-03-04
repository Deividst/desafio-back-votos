package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.deividst.votos.enums.RecordStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResultVoteDto {

    private Long recordId;
    private Long countVoteYes;
    private Long countVoteNo;
    private Long totalVote;
    private RecordStatusEnum result;

}
