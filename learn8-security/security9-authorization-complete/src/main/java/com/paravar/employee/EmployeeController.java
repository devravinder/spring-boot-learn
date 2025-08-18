package com.paravar.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees (restricted to Manager, HR, CEO)
    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGER', 'HR')")
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }

    // Get single employee (restricted similarly)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR')")
    public EmployeeWithManagerDTO getById(@PathVariable Long id) {
        return employeeService.getById(id)
                .orElseThrow(()->EmployeeNotFoundException.of(id));
    }

    // Create employee (HR and CEO only; no CEO creation via API to enforce only one CEO)
    @PostMapping
    @PreAuthorize("hasRole('HR')")
    public EmployeeDTO create(@RequestBody EmployeeCreateCmd employee) {
        return employeeService.create(employee);
    }

    // Update employee (HR/CEO all, Managers only direct reports)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HR') or (hasRole('MANAGER') and @employeeService.isManagerOfEmployee(#id, authentication.principal.id))")
    public EmployeeDTO update(@PathVariable Long id, @RequestBody EmployeeUpdateCmd updatedEmployee) {
        return employeeService.update(id, updatedEmployee);
    }

    // Delete employee (HR, MANAGER & CEO only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('HR', 'MANAGER')")
    public void delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    // Promote to manager (CEO only, only for employees)
    @PutMapping("/{id}/promote-to-manager")
  //  @PreAuthorize("hasRole('CEO')")
    public void promoteToManager(@PathVariable Long id) {
         employeeService.promoteToManager(id);
    }

    @PutMapping("/{username}/update-password")
    @PreAuthorize("hasAnyRole('MANAGER', 'HR') or (#username == authentication.principal.username)")
    public void updatePassword(@PathVariable String username, @RequestBody UpdatePasswordCmd updatePasswordCmd){
        employeeService.updatePassword(username, updatePasswordCmd);
    }


}