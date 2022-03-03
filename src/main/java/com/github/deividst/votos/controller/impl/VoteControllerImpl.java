package com.github.deividst.votos.controller.impl;

import com.github.deividst.votos.dtos.VoteDto;
import com.github.deividst.votos.service.VoteService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Vote")
@RestController
@RequestMapping("v1/vote")
public class VoteControllerImpl {

    private final VoteService voteService;

    public VoteControllerImpl(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<Void> processVote(@RequestBody VoteDto voteDto) {
        this.voteService.processVote(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
