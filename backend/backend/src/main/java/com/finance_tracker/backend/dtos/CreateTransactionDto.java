package com.finance_tracker.backend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance_tracker.backend.models.TransactionType;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CreateTransactionDto {

    @NotNull
    private LocalDate date;  // Accepts "YYYY-MM-DD" from frontend

    @JsonProperty("user") // FE sends "user"
    private Long userId;

    @NotNull
    private TransactionType type;

    @NotNull
    @PositiveOrZero
    private Double amount;

    @NotBlank
    private String description;

    @NotNull
    @JsonProperty("category") // FE sends "category"
    private Long categoryId;
}
