package com.paravar.employee;

import com.paravar.user.EmployeeDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "manager.id", source = "managerId")
    Employee map(EmployeeCreateCmd employeeCreateCmd);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "manager.id", source = "managerId")
    Employee map(EmployeeUpdateCmd employeeUpdateCmd);


    @Mapping(target = "manager.id", source = "managerId")
    Employee map(EmployeeDetails employeeDetails);

    @Mapping(target = "managerId", source = "manager.id")
    EmployeeDTO toDto(Employee employee);
}
