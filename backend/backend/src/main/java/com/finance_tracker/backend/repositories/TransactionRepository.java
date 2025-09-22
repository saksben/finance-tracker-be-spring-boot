package com.finance_tracker.backend.repositories;

import com.finance_tracker.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
