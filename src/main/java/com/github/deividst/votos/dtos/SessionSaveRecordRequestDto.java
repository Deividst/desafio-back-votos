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

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_FINAL_DATE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_FINAL_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_INITIAL_DATE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_INITIAL_EXAMPLE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessionSaveRecordRequestDto {

    @Schema(required = true, description = SESSION_INITIAL_DATE_DESCRIPTION, example = SESSION_INITIAL_EXAMPLE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime initialDate;

    @Schema(description = SESSION_FINAL_DATE_DESCRIPTION, example = SESSION_FINAL_EXAMPLE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime finalDate;

}
