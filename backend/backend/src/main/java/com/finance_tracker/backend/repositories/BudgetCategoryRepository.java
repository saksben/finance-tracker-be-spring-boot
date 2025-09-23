package com.finance_tracker.backend.repositories;

import com.finance_tracker.backend.models.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Long> {
}
