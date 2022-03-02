package com.github.deividst.votos.controller;

import com.github.deividst.votos.dtos.RecordDto;
import com.github.deividst.votos.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<RecordDto> save(@Valid @RequestBody RecordDto recordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.recordService.saveRecord(recordDto));
    }

}
