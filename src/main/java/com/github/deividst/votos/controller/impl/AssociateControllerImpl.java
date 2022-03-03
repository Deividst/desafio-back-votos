package com.github.deividst.votos.controller.impl;

import com.github.deividst.votos.controller.AssociateController;
import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.service.AssociateService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Associate")
@RestController
@RequestMapping("v1/associates")
public class AssociateControllerImpl implements AssociateController {

    private final AssociateService associateService;

    public AssociateControllerImpl(AssociateService associateService) {
        this.associateService = associateService;
    }

    @PostMapping
    public ResponseEntity<AssociateResponseDto> save(AssociateSaveRequestDto associateDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.associateService.save(associateDto));
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<AssociateResponseDto> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.status(HttpStatus.OK).body(this.associateService.findByCpf(cpf));
    }

}
