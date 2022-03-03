package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.CPFIntegrationDto;
import com.github.deividst.votos.enums.StatusCPFIntegrationEnum;
import com.github.deividst.votos.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CPFIntegrationService {

    private final RestTemplate restTemplate;

    @Value("${url-cpf-integration}")
    private String url;

    public CPFIntegrationService() {
        this.restTemplate = new RestTemplate();
    }

    public void checkCPF(String cpf) {
        ResponseEntity<CPFIntegrationDto> response = restTemplate.getForEntity(url + cpf, CPFIntegrationDto.class);

        if (response.getBody().getStatus().equals(StatusCPFIntegrationEnum.UNABLE_TO_VOTE)) {
            throw new BusinessException("O associado informado não está apto a votar");
        }
    }

}
