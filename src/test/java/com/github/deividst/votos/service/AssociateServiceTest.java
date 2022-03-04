package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.model.Associate;
import com.github.deividst.votos.repository.AssociateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssociateServiceTest {

    @InjectMocks
    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;

    @Test
    void givenSaveAssociate_shouldReturnSuccess() {

        when(associateRepository.save(any(Associate.class))).thenReturn(buildAssociate());

        AssociateSaveRequestDto requestDto = buildAssociateSaveRequestDto();
        AssociateResponseDto responseDto = associateService.save(requestDto);

        verify(associateRepository, times(1)).save(any(Associate.class));
        Assertions.assertEquals(requestDto.getCpf(), responseDto.getCpf());
    }

    @Test
    void givenFindByCpf_whenCpfExist_shouldReturnSuccess() {
        Associate associate = buildAssociate();
        when(associateRepository.findByCpf(any())).thenReturn(associate);

        AssociateResponseDto responseDto = associateService.findByCpf(any());

        verify(associateRepository, times(1)).findByCpf(any());
        Assertions.assertEquals(associate.getCpf(), responseDto.getCpf());
    }

    @Test
    void givenFindByCpf_whenCpfNotExist_shouldReturnEntityNotFoundException() {

        when(associateRepository.findByCpf(any())).thenThrow(EntityNotFoundException.class);

        Assertions.assertThrows(EntityNotFoundException.class, () ->
                associateService.findByCpf(any())
        );
    }

    private AssociateSaveRequestDto buildAssociateSaveRequestDto() {
        return AssociateSaveRequestDto.builder()
                .cpf("36572935078")
                .name("teste")
                .build();
    }

    private Associate buildAssociate() {
        return Associate.builder()
                .cpf("36572935078")
                .name("test")
                .registerDate(LocalDateTime.now())
                .build();
    }
}