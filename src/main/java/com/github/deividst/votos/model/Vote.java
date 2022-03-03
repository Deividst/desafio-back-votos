package com.github.deividst.votos.model;

import com.github.deividst.votos.converter.VoteTypeEnumConverter;
import com.github.deividst.votos.enums.TypeVoteEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "TB_VOTE")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long voteId;

    @OneToOne
    @JoinColumn(name = "ASSOCIATE_ID", referencedColumnName = "id")
    private Associate associate;

    @Convert(converter = VoteTypeEnumConverter.class)
    @Column(name = "VOTE_TYPE")
    private TypeVoteEnum voteType;

    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

}
