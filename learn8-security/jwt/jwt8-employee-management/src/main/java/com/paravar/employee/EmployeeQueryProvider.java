package com.paravar.employee;

import com.paravar.domain.Employee;
import com.paravar.employee.model.EmployeeListItem;
import com.paravar.employee.port.inbound.EmployeeQuery;
import com.paravar.employee.port.outbound.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class EmployeeQueryProvider implements EmployeeQuery {
    private final EmployeeRepository employeeRepository;

    @Override
    public Optional<EmployeeListItem> findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id);
    }

    @Override
    public Optional<Employee> findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }


    @Override
    public Slice<EmployeeListItem> findAll(Pageable page) {
        return employeeRepository.findAll(page);
    }

    @Override
    public Slice<EmployeeListItem> findReporters(Long managerId, Pageable page) {
        return employeeRepository.findReporters(managerId,page);
    }
}
