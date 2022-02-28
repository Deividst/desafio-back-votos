package com.github.deividst.votos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "TB_ASSOCIATE")
@SequenceGenerator(name = "SQ_ASSOCIATE", sequenceName = "SQ_ASSOCIATE", allocationSize = 1)
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ASSOCIATE")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

}
