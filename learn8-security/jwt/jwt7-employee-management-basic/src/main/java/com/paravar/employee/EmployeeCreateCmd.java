package com.paravar.employee;

import com.paravar.models.Role;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record EmployeeCreateCmd(@NotNull String username, @NotNull String password, @NotNull Role role, Long managerId) {
}
