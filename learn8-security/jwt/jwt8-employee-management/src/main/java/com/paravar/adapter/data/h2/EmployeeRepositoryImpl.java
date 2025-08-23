package com.paravar.adapter.data.h2;

import com.paravar.employee.model.EmployeeListItem;
import com.paravar.domain.Employee;
import com.paravar.employee.model.CreateEmployee;
import com.paravar.employee.port.outbound.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeH2Repository employeeH2Repository;
    private final EmployeeMapper mapper;

    @Override
    public Optional<EmployeeListItem> findEmployeeById(Long id) {
        return employeeH2Repository.findEmployeeById(id);
    }

    @Override
    public Optional<Employee> findByUsername(String username) {
        return employeeH2Repository.findByUsernameAndDeletedAtIsNull(username);
    }

    @Override
    public Slice<EmployeeListItem> findAll(Pageable page) {
        return employeeH2Repository.findEmployees(page);
    }

    @Override
    public Slice<EmployeeListItem> findReporters(Long mangerId, Pageable page) {
        return employeeH2Repository.findReporters(mangerId,page);
    }

    @Override
    public Employee createEmployee(CreateEmployee createEmployee) {
        return employeeH2Repository.save(mapper.map(createEmployee));
    }

}
