package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.deividst.votos.enums.TypeVoteEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ASSOCIATE_ID_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.RECORD_ID_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.VOTE_TYPE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.VOTE_TYPE_EXAMPLE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoteDto {

    @NotNull(message = "O campo associateId é obrigatório")
    @Schema(required = true, description = ASSOCIATE_ID_DESCRIPTION, example = ID_EXAMPLE)
    private Long associateId;

    @NotNull(message = "O campo recordId é obrigatório")
    @Schema(required = true, description = RECORD_ID_DESCRIPTION, example = ID_EXAMPLE)
    private Long recordId;

    @Schema(required = true, description = VOTE_TYPE_DESCRIPTION, example = VOTE_TYPE_EXAMPLE)
    @NotNull(message = "O campo voteType é obrigatório")
    private TypeVoteEnum voteType;

}
