package com.bnp.manualtransaction.domain.repository;

import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManualTransactionRepository extends JpaRepository<ManualTransactionEntity, ManualTransactionId> {

    @Query("SELECT MAX(e.id.entryNumber) FROM ManualTransactionEntity e WHERE e.id.month = :month AND e.id.year = :year")
    Optional<Integer> findMaxEntryNumberByMonthAndYear(Integer month, Integer year);
}
