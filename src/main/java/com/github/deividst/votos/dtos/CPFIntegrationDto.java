package com.github.deividst.votos.dtos;

import com.github.deividst.votos.enums.StatusCPFIntegrationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CPFIntegrationDto {

    private StatusCPFIntegrationEnum status;

}
