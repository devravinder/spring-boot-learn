package com.paravar.employee;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordCmd(@NotNull String currentPassword, @NotNull String newPassword) {
}
