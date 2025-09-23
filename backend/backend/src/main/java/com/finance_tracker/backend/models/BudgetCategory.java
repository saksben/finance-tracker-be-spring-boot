package com.finance_tracker.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finance_tracker.backend.models.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "budget_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float estimate;

    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    @JsonIgnoreProperties("categories")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnoreProperties("budgets")
    private Category category;
}
