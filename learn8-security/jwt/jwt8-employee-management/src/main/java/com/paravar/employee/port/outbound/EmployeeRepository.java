package com.paravar.employee.port.outbound;

import com.paravar.domain.Employee;
import com.paravar.employee.model.CreateEmployee;
import com.paravar.employee.model.EmployeeListItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface EmployeeRepository {
    Optional<EmployeeListItem> findEmployeeById(Long id);
    Optional<Employee> findByUsername(String username);
    Slice<EmployeeListItem> findAll(Pageable page);
    Slice<EmployeeListItem> findReporters(Long mangerId, Pageable page);
    Employee createEmployee(CreateEmployee createEmployee);

}
