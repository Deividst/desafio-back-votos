package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.mapper.AssociateMapper;
import com.github.deividst.votos.model.Associate;
import com.github.deividst.votos.repository.AssociateRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
public class AssociateService {

    private final AssociateRepository associateRepository;

    public AssociateService(AssociateRepository associateRepository) {
        this.associateRepository = associateRepository;
    }

    public AssociateResponseDto save(AssociateSaveRequestDto associateDto){
        Associate associateEntity = AssociateMapper.toEntity(associateDto);
        associateEntity = this.associateRepository.save(associateEntity);
        return AssociateMapper.toDto(associateEntity);
    }

    public AssociateResponseDto findByCpf(String cpf) {
        Associate associateEntity = this.associateRepository.findByCpf(cpf);

        if (Objects.isNull(associateEntity)) {
            throw new EntityNotFoundException("Nenhum registro encontrato com o cpf informado.");
        }

        return AssociateMapper.toDto(associateEntity);
    }

}
