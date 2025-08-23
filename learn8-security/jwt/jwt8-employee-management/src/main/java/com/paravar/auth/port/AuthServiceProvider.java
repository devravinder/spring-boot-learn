package com.paravar.auth.port;

import com.paravar.adapter.web.auth.LoginRequest;
import com.paravar.auth.port.inbound.AuthService;
import com.paravar.employee.model.EmployeeNotFoundException;
import com.paravar.employee.model.PasswordNotMatchException;
import com.paravar.employee.port.inbound.EmployeeQuery;
import com.paravar.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthServiceProvider implements AuthService {

    private final EmployeeQuery employeeQuery;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest request) {

        return employeeQuery.findByUsername(request.username())
                .map(emp -> {

                    if (!passwordEncoder.matches(request.password(), emp.getPassword())) {
                        throw PasswordNotMatchException.of();
                    }

                    return jwtUtil.generateToken(emp.getId(), emp.getName(), emp.getUsername(), emp.getRole());
                })
                .orElseThrow(() -> EmployeeNotFoundException.of(request.username()));

    }
}
