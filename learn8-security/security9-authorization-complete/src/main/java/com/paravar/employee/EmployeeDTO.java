package com.paravar.employee;

import com.paravar.models.Role;

public record EmployeeDTO(Long id, String username, Role role, Long managerId) {
    public static EmployeeDTO fromEmployee(Employee employee) {
        Long managerId = employee.getManager() != null ? employee.getManager().getId() : null;
        return new EmployeeDTO(employee.getId(), employee.getUsername(), employee.getRole(), managerId);
    }
}