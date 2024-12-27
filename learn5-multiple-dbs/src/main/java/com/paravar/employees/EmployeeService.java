package com.paravar.employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    public final EmployeeRepository employeeRepository;

    public void saveEmployee() {
        Employee employee = Employee.builder().name("John").email("employee@emai.com").age(30).build();
        employeeRepository.save(employee);
    }
}
