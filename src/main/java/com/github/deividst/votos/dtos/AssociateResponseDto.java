package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.CPF_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.CPF_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.NAME_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.NAME_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.REGISTER_DATE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.REGISTER_DATE_EXAMPLE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssociateResponseDto {

    @Schema(required = true, description = ID_DESCRIPTION, example = ID_EXAMPLE)
    private Long id;

    @Schema(required = true, description = NAME_DESCRIPTION, example = NAME_EXAMPLE)
    private String name;

    @Schema(required = true, description = CPF_DESCRIPTION, example = CPF_EXAMPLE)
    private String cpf;

    @Schema(required = true, description = REGISTER_DATE_DESCRIPTION, example = REGISTER_DATE_EXAMPLE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime registerDate;

}
