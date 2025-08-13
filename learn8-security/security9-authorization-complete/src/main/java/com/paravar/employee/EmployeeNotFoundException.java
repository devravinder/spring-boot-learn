package com.paravar.employee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public static EmployeeNotFoundException of(Long id) {
        return new EmployeeNotFoundException("Employee with id " + id + " not found");
    }

    public static EmployeeNotFoundException of(String username) {
        return new EmployeeNotFoundException("Employee with username " + username + " not found");
    }
}
