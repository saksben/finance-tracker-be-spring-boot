package com.finance_tracker.backend.models;

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
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
