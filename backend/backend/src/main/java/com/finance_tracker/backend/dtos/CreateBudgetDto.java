package com.finance_tracker.backend.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CreateBudgetDto {

    @NotBlank
    private String name;

    @NotNull
    @Min(0)
    private Float estimatedRevenue;

    @NotNull
    private List<UserEstimate> users;

    @NotNull
    private List<CategoryEstimate> categories;

    private Boolean alertOverbudget = false;
    private Boolean alertOverAmount = false;

    @Min(0)
    private Float alertAmount;

    private Boolean overbudget = false;

    @Data
    public static class UserEstimate {
        @NotNull
        private Long id;
        @NotNull
        private Float estimate;
    }

    @Data
    public static class CategoryEstimate {
        @NotNull
        private Long id;
        @NotNull
        private Float estimate;
    }
}
