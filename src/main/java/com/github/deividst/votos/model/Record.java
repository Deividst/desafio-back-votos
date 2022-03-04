package com.github.deividst.votos.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.github.deividst.votos.converter.RecordStatusEnumConverter;
import com.github.deividst.votos.enums.RecordStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
@Entity(name = "TB_RECORD")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "id")
    private Session session;

    @Convert(converter = RecordStatusEnumConverter.class)
    @Column(name = "STATUS")
    private RecordStatusEnum status;

}
