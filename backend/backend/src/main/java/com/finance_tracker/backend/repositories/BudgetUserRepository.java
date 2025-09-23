package com.finance_tracker.backend.repositories;

import com.finance_tracker.backend.models.BudgetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetUserRepository extends JpaRepository<BudgetUser, Long> {
}
