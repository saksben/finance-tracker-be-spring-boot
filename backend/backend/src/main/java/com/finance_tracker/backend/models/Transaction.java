package com.finance_tracker.backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd") // To conform to the frontend
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"transactions", "budgets"})
    private User user;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private Double amount;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"transactions", "budgets"})
    private Category category;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    @JsonIgnoreProperties("transactions")
    private Budget budget;
}
