package com.paravar.adapter.web.employee;

import com.paravar.auth.model.AuthPrincipal;
import com.paravar.domain.ErrorResponse;
import com.paravar.employee.model.EmployeeListItem;
import com.paravar.employee.model.EmployeeNotFoundException;
import com.paravar.employee.port.inbound.EmployeeQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeResource {

    private final EmployeeQuery employeeQuery;

    @GetMapping("/self")
    public EmployeeListItem self(@AuthenticationPrincipal AuthPrincipal authPrincipal) {
        return employeeQuery.findEmployeeById(authPrincipal.id())
                .orElseThrow(() -> EmployeeNotFoundException.of(authPrincipal.id()));
    }

    @GetMapping("/{id}")
    public EmployeeListItem getEmployee(@PathVariable("id") Long id) {
        return employeeQuery.findEmployeeById(id)
                .orElseThrow(() -> EmployeeNotFoundException.of(id));
    }

    @GetMapping()
    //eg: /api/employees/2/reporters?pageSize=3&pageNumber=1&sortBy=id:asc
    public Slice<EmployeeListItem> findAll( Integer pageNumber, Integer pageSize, String... sortBy) {
        return employeeQuery.findAll(toPageable(pageNumber,pageSize,sortBy));
    }

    @GetMapping("/{id}/reporters")
    //eg: /api/employees/2/reporters?pageSize=3&pageNumber=1&sortBy=id:asc
    public Slice<EmployeeListItem> findReporters(@PathVariable("id") Long id, Integer pageNumber, Integer pageSize, String... sortBy) {
        return employeeQuery.findReporters(id, toPageable(pageNumber,pageSize,sortBy));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(ex.getMessage())
                .validationErrors(Collections.emptyMap())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    private Pageable toPageable( Integer pageNumber, Integer pageSize, String... sortBy) {

        if (pageSize == null || pageSize < 0)
            pageSize = 10;
        pageSize = Math.min(10, pageSize);


        if (pageNumber == null || pageNumber < 0)
            pageNumber = 0;

        Sort sort = null;
        if (sortBy != null) {
            for (String str : sortBy) {
                String[] parts = str.split(":");
                Sort newSort = parts[1] != null ? Sort.by(Sort.Direction.valueOf(parts[1].toUpperCase()), parts[0]) : Sort.by(parts[0]);
                if (sort == null)
                    sort = newSort;

                else sort.and(newSort);
            }
        }

        return sort == null ? PageRequest.of(pageNumber, pageSize) : PageRequest.of(pageNumber, pageSize, sort);
    }


}
