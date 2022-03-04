package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.CPF_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.CPF_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.NAME_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.NAME_EXAMPLE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssociateSaveRequestDto {

    @NotBlank(message = "O campo name é obrigatório")
    @Schema(required = true, description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
    private String name;

    @NotBlank(message = "O campo cpf é obrigatório")
    @Schema(required = true, description = CPF_DESCRIPTION, example = CPF_EXAMPLE)
    private String cpf;

}
