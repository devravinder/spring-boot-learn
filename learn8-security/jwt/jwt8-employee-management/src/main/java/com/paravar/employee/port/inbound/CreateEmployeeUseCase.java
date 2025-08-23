package com.paravar.employee.port.inbound;

import com.paravar.domain.Employee;
import com.paravar.employee.model.CreateEmployee;

public interface CreateEmployeeUseCase {
    Employee createEmployee(CreateEmployee createEmployee);
}
