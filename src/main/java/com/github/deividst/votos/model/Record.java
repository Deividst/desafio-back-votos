package com.github.deividst.votos.model;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "TB_RECORD")
@SequenceGenerator(name = "SQ_RECORD", sequenceName = "SQ_RECORD", allocationSize = 1)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_RECORD")
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REGISTER_DATE")
    private LocalDateTime registerDate;

    @OneToOne
    @JoinColumn(name = "SESSION_ID", referencedColumnName = "id")
    private Session session;

    @OneToMany
    @JoinColumn(name = "RECORD_ID", referencedColumnName = "id")
    private Set<Vote> votes;

    @Convert(converter = RecordStatusEnumConverter.class)
    @Column(name = "STATUS")
    private RecordStatusEnum status;

}
