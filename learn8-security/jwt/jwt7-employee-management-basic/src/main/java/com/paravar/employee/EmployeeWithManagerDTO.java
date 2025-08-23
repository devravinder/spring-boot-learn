package com.paravar.employee;

import com.paravar.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWithManagerDTO {
    private Long id;
    private String username;
    private Role role;
    
    // Manager details
    private Long managerId;
    private String managerUsername;
    private Role managerRole;
}