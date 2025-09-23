package com.finance_tracker.backend.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum TransactionType {
    @JsonProperty("Expense") // Conforms to frontend
    EXPENSE,
    @JsonProperty("Revenue") // Conforms to frontend
    REVENUE;

    // Converts to Uppercase to comply with frontend
    @JsonCreator
    public static TransactionType from(String value) {
        return TransactionType.valueOf(value.toUpperCase());
    }
}
