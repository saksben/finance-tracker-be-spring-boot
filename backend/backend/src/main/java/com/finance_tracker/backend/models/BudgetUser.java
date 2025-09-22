package com.finance_tracker.backend.models;

import com.finance_tracker.backend.models.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "budget_user", uniqueConstraints = @UniqueConstraint(columnNames = {"budget_id", "user_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float estimate;

    @ManyToOne
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
