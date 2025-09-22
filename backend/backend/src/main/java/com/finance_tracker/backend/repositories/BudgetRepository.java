package com.finance_tracker.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance_tracker.backend.models.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
