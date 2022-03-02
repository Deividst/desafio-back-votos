package com.github.deividst.votos.repository;

import com.github.deividst.votos.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
