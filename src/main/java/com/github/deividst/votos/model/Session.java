package com.github.deividst.votos.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "TB_SESSION")
@SequenceGenerator(name = "SQ_SESSION", sequenceName = "SQ_SESSION", allocationSize = 1)
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SESSION")
    @Column(name = "ID")
    private Long id;

    @OneToOne(mappedBy = "session", cascade = CascadeType.ALL)
    private Record record;

    @Column(name = "INITIAL_DATE")
    private LocalDateTime initialDate;

    @Column(name = "FINAL_DATE")
    private LocalDateTime finalDate;

}
