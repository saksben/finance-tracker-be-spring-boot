package com.finance_tracker.backend.dtos;

import com.finance_tracker.backend.models.TransactionType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateTransactionDto {

    @NotNull
    private LocalDateTime date;

    private Long userId;

    @NotNull
    private TransactionType type;

    @NotNull
    @PositiveOrZero
    private Double amount;

    @NotBlank
    private String description;

    @NotNull
    private Long categoryId;
}
