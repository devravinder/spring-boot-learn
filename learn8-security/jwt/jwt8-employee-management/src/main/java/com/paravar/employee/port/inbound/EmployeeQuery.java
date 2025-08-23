package com.paravar.employee.port.inbound;

import com.paravar.domain.Employee;
import com.paravar.employee.model.EmployeeListItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface EmployeeQuery {
    Optional<Employee> findByUsername(String username);

    @PostAuthorize("@access.canFetchEmployeeDetails(returnObject.orElse(null))")
    Optional<EmployeeListItem> findEmployeeById(Long id);

    @PreAuthorize("@access.canFetchReporters(principal, #managerId)")
    Slice<EmployeeListItem> findReporters(@P("managerId") Long managerId, Pageable page);

    @PreAuthorize("@access.isCTO(principal)")
    Slice<EmployeeListItem> findAll(Pageable page);

}
