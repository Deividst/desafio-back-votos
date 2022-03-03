package com.github.deividst.votos.controller.impl;

import com.github.deividst.votos.controller.SessionController;
import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.dtos.SessionSaveRequestDto;
import com.github.deividst.votos.service.SessionService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Session")
@RestController
@RequestMapping("v1/session")
public class SessionControllerImpl implements SessionController {

    private final SessionService sessionService;

    public SessionControllerImpl(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<SessionResponseDto> save(@RequestBody SessionSaveRequestDto sessionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.sessionService.save(sessionDto));
    }

    @GetMapping
    public ResponseEntity<List<SessionResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.sessionService.findAll());
    }

}
