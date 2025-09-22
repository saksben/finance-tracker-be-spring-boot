package com.finance_tracker.backend.services;

import com.finance_tracker.backend.dtos.*;
import com.finance_tracker.backend.models.Budget;
import com.finance_tracker.backend.models.BudgetCategory;
import com.finance_tracker.backend.models.BudgetUser;
import com.finance_tracker.backend.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public java.util.List<Budget> getBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudget(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Budget with id " + id + " not found"));
    }

    @Transactional
    public Budget createBudget(CreateBudgetDto dto) {
        Budget budget = Budget.builder()
                .name(dto.getName())
                .estimatedRevenue(dto.getEstimatedRevenue())
                .alertOverbudget(dto.getAlertOverbudget())
                .alertOverAmount(dto.getAlertOverAmount())
                .alertAmount(dto.getAlertAmount())
                .overbudget(dto.getOverbudget())
                .build();

        budget.setUsers(dto.getUsers().stream().map(userEstimate -> {
            var user = userRepository.findById(userEstimate.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found: " + userEstimate.getId()));
            return BudgetUser.builder()
                    .budget(budget)
                    .user(user)
                    .estimate(userEstimate.getEstimate())
                    .build();
        }).collect(Collectors.toList()));

        budget.setCategories(dto.getCategories().stream().map(catEstimate -> {
            var category = categoryRepository.findById(catEstimate.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found: " + catEstimate.getId()));
            return BudgetCategory.builder()
                    .budget(budget)
                    .category(category)
                    .estimate(catEstimate.getEstimate())
                    .build();
        }).collect(Collectors.toList()));

        return budgetRepository.save(budget);
    }

    @Transactional
    public Budget updateBudget(Long id, UpdateBudgetDto dto) {
        Budget budget = getBudget(id);

        if (dto.getName() != null) budget.setName(dto.getName());
        if (dto.getEstimatedRevenue() != null) budget.setEstimatedRevenue(dto.getEstimatedRevenue());
        if (dto.getAlertOverbudget() != null) budget.setAlertOverbudget(dto.getAlertOverbudget());
        if (dto.getAlertOverAmount() != null) budget.setAlertOverAmount(dto.getAlertOverAmount());
        if (dto.getAlertAmount() != null) budget.setAlertAmount(dto.getAlertAmount());
        if (dto.getOverbudget() != null) budget.setOverbudget(dto.getOverbudget());

        // For simplicity, remove existing Users and replace
        if (dto.getUsers() != null) {
            budget.getUsers().clear();
            budget.getUsers().addAll(dto.getUsers().stream().map(userEstimate -> {
                var user = userRepository.findById(userEstimate.getId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found: " + userEstimate.getId()));
                return BudgetUser.builder()
                        .budget(budget)
                        .user(user)
                        .estimate(userEstimate.getEstimate())
                        .build();
            }).collect(Collectors.toList()));
        }

        // For simplicity, remove existing Users and replace
        if (dto.getCategories() != null) {
            budget.getCategories().clear();
            budget.getCategories().addAll(dto.getCategories().stream().map(catEstimate -> {
                var category = categoryRepository.findById(catEstimate.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Category not found: " + catEstimate.getId()));
                return BudgetCategory.builder()
                        .budget(budget)
                        .category(category)
                        .estimate(catEstimate.getEstimate())
                        .build();
            }).collect(Collectors.toList()));
        }

        return budgetRepository.save(budget);
    }

    public void deleteBudget(Long id) {
        Budget budget = getBudget(id);
        budgetRepository.delete(budget);
    }
}
