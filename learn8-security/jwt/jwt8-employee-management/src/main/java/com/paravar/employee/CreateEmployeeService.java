package com.paravar.employee;

import com.paravar.domain.Employee;
import com.paravar.employee.model.CreateEmployee;
import com.paravar.employee.port.inbound.CreateEmployeeUseCase;
import com.paravar.employee.port.outbound.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
class CreateEmployeeService implements CreateEmployeeUseCase {
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(CreateEmployee createEmployee) {
        return employeeRepository.createEmployee(createEmployee);
    }
}
