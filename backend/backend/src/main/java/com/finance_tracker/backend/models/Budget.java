package com.finance_tracker.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.finance_tracker.backend.models.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "budgets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Float estimatedRevenue;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("budget")
    private List<BudgetCategory> categories;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("budget")
    private List<BudgetUser> users;

    private Boolean alertOverbudget = false;
    private Boolean alertOverAmount = false;

    private Float alertAmount;

    private Boolean overbudget = false;
}
