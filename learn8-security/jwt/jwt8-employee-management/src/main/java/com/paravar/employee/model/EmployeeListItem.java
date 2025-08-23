package com.paravar.employee.model;

import com.paravar.domain.Role;

public record EmployeeListItem(Long id, String username, String name, Role role, Long managerId, String managerName) {
}
