package com.finance_tracker.backend.controllers;

import com.finance_tracker.backend.dtos.*;
import com.finance_tracker.backend.models.Budget;
import com.finance_tracker.backend.services.BudgetService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @GetMapping
    public List<Budget> getBudgets() {
        return budgetService.getBudgets();
    }

    @GetMapping("/{id}")
    public Budget getBudget(@PathVariable Long id) {
        return budgetService.getBudget(id);
    }

    @PostMapping
    public Budget createBudget(@RequestBody CreateBudgetDto dto) {
        return budgetService.createBudget(dto);
    }

    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody UpdateBudgetDto dto) {
        return budgetService.updateBudget(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
        budgetService.deleteBudget(id);
        return ResponseEntity.ok("Budget with id " + id + " deleted successfully");
    }
}
