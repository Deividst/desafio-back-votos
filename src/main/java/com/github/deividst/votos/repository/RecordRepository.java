package com.github.deividst.votos.repository;

import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE TB_RECORD SET status = :statusEnum WHERE id = :recordId")
    void updateStatus(@Param("statusEnum") RecordStatusEnum statusEnum, @Param("recordId") Long recordId);

}
