package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.CPFIntegrationDto;
import com.github.deividst.votos.enums.StatusCPFIntegrationEnum;
import com.github.deividst.votos.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class CPFIntegrationService {

    private final RestTemplate restTemplate;

    @Value("${url-cpf-integration}")
    private String url;

    public CPFIntegrationService() {
        this.restTemplate = new RestTemplate();
    }

    public void checkCPF(String cpf) {
        log.info("CPFIntegrationService.checkCPF() - CPF: {} serviço de integração: {}", cpf, url);
        ResponseEntity<CPFIntegrationDto> response;

        try {
             response = restTemplate.getForEntity(url + cpf, CPFIntegrationDto.class);
        } catch (Exception e) {
            log.error("CPFIntegrationService.checkCPF(): Erro na comunicação com serviço:", e);
            throw e;
        }

        log.info("CPFIntegrationService.checkCPF() - Status {} - Response: {}", response.getStatusCode(), response.getBody());

        if (Objects.nonNull(response.getBody())
                && response.getBody().getStatus().equals(StatusCPFIntegrationEnum.UNABLE_TO_VOTE)) {
            throw new BusinessException("O associado informado não está apto a votar");
        }
    }

}
