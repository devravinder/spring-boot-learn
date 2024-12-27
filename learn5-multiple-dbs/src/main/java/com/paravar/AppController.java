package com.paravar;

import com.paravar.employees.EmployeeService;
import com.paravar.users.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AppController {
    private final EmployeeService employeeService;
    private final UserService userService;
    @RequestMapping()
    public String hello() {
        employeeService.saveEmployee();
        userService.saveUser();
        return "Hello World!";
    }
}
