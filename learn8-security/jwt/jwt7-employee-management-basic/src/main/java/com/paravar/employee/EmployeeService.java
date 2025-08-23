package com.paravar.employee;

import com.paravar.models.Role;
import com.paravar.user.EmployeeDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeValidator validator;
    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final  EmployeeMapper mapper;

    @Transactional(readOnly = true)
    public boolean isManagerOfEmployee(Long employeeId, Long managerId) {
        EmployeeWithManagerDTO employee = repository.findByIdWithManager(employeeId).orElse(null);
        if (employee == null || employee.getManagerUsername() == null) {
            return false;
        }
        return employee.getManagerId().equals(managerId);
    }

    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAll() {

        //  repository.findAll();
        return repository.findAllWithManagerId();
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeWithManagerDTO> getById(@PathVariable Long id) {

      //  return repository.findById(id);
        return  repository.findByIdWithManager(id);
    }

    @Transactional
    public EmployeeDTO create(EmployeeCreateCmd employeeCreateCmd) {

        validator.validateCreation(employeeCreateCmd);

        Employee employee = mapper.map(employeeCreateCmd);

        // encode password
        employee.setPassword(encodePassword(employee.getPassword()));

        return mapper.toDto(repository.save(employee));
    }

    @Transactional
    public EmployeeDTO update(Long id, EmployeeUpdateCmd employeeUpdateCmd) {

        return repository.findById(id)
                .map(old -> {

                    Employee updateData = mapper.map(employeeUpdateCmd);

                    old.setRole(updateData.getRole());
                    old.setManager(updateData.getManager());

                    return mapper.toDto(repository.save(old));
                })
                .orElseThrow(() -> EmployeeNotFoundException.of(id));

    }

    @Transactional
    public void deleteEmployee(Long id) {

        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw EmployeeNotFoundException.of(id);
        }

    }

    @Transactional
    public void promoteToManager(Long id) {
        repository.findById(id)
                .map(old -> {
                    validator.validatePromotion(old);
                    old.setRole(Role.MANAGER);
                    return repository.save(old);
                })
                .orElseThrow(() -> EmployeeNotFoundException.of(id));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "userDetails", key = "#username")
    public EmployeeDetails findByUsername(String username){
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username "+username));

    }


    @Transactional
    @CacheEvict(value = "userDetails", key = "#username")
    public void updatePassword(String username, UpdatePasswordCmd updatePasswordCmd){
        repository.findByUsername(username)
                .map(employeeDetails -> {

                    validator.validateUpdatePassword(updatePasswordCmd, employeeDetails);

                    Employee employee = mapper.map(employeeDetails);
                    employee.setPassword(encodePassword(updatePasswordCmd.newPassword()));

                    return repository.save(employee);
                })
                .orElseThrow(() -> EmployeeNotFoundException.of(username));
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }



}