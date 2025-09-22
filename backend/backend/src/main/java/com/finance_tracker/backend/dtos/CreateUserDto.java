package com.finance_tracker.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotBlank
    private String name;
}
