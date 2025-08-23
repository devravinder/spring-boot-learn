package com.paravar.employee;

import com.paravar.models.Role;
import com.paravar.user.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeValidator {

    private final PasswordEncoder passwordEncoder;

    public void validateCreation(EmployeeCreateCmd employee){
        if (employee.role() == Role.CEO) {
            throw new IllegalArgumentException("Cannot create another CEO");
        }
    }

    public void validatePromotion(Employee employee){
        if(employee.getRole() != Role.EMPLOYEE){
            throw new IllegalArgumentException("Only employees can be promoted");
        }
    }

    public void validateUpdatePassword(UpdatePasswordCmd updatePasswordCmd, EmployeeDetails employee){
        if(!passwordEncoder.matches(updatePasswordCmd.currentPassword(), employee.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }
    }


}
