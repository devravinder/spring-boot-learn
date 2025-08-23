package com.paravar.employee.model;

import com.paravar.domain.Role;

public record CreateEmployee(String username, String password , String name, Role role, Long managerId) {
}
