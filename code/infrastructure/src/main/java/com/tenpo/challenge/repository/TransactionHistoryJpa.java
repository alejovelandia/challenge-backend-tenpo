package com.tenpo.challenge.repository;

import com.tenpo.challenge.repository.entity.TransactionHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryJpa extends JpaRepository<TransactionHistoryEntity, Long> {

}
