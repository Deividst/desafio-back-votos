package com.github.deividst.votos.controller.impl;

import com.github.deividst.votos.controller.RecordController;
import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import com.github.deividst.votos.service.RecordService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Records")
@RestController
@RequestMapping("v1/records")
public class RecordControllerImpl implements RecordController {

    private final RecordService recordService;

    public RecordControllerImpl(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    public ResponseEntity<RecordResponseDto> save(RecordSaveRequestDto recordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.recordService.save(recordDto));
    }

    @GetMapping
    public ResponseEntity<List<RecordResponseDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.recordService.findAll());
    }

}
