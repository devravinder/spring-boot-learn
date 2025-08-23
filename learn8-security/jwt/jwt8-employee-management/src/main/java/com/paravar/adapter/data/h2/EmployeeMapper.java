package com.paravar.adapter.data.h2;

import com.paravar.domain.Employee;
import com.paravar.employee.model.CreateEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
 public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "manager.id", source = "managerId")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    Employee map(CreateEmployee createEmployee);
}
