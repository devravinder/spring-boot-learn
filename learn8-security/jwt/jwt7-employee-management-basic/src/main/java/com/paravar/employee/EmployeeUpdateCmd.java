package com.paravar.employee;

import com.paravar.models.Role;

public record EmployeeUpdateCmd(Role role, Long managerId) {
}
