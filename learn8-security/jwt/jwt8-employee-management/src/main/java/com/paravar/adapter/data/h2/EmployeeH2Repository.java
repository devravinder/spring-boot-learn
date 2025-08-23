package com.paravar.adapter.data.h2;

import com.paravar.domain.Employee;
import com.paravar.domain.Role;
import com.paravar.employee.model.EmployeeListItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeH2Repository extends JpaRepository<Employee, Long> {


    /*

    For DTOs: Use regular LEFT JOIN without FETCH
    For Entities: Use LEFT JOIN FETCH and return Employee entity
    Best Practice: Use @EntityGraph for cleaner entity loading


    * */


    //    @EntityGraph(attributePaths = {"manager"})
    Optional<Employee> findByUsernameAndDeletedAtIsNull(String username);


    @Query("""
            SELECT new com.paravar.employee.model.EmployeeListItem(
            e.id, e.username, e.name, e.role, m.id, m.name
            )
            FROM Employee e LEFT JOIN e.manager m
            WHERE e.id = :id AND e.deletedAt IS NULL
            """)
    Optional<EmployeeListItem> findEmployeeById(@Param("id") Long id);


    @Query("""
            SELECT new com.paravar.employee.model.EmployeeListItem(
                e.id, e.username, e.name, e.role, m.id, m.name
            )
            FROM Employee e LEFT JOIN e.manager m
            WHERE e.deletedAt IS NULL
            """)
    Slice<EmployeeListItem> findEmployees(Pageable page);

    @Query("""
            SELECT new com.paravar.employee.model.EmployeeListItem(
                e.id, e.username, e.name, e.role, m.id, m.name
            )
            FROM Employee e LEFT JOIN e.manager m
            WHERE e.manager.id = :managerId AND e.deletedAt IS NULL
            """)
    Slice<EmployeeListItem> findReporters(@Param("managerId") Long managerId, Pageable page);

    @Query("""
            SELECT new com.paravar.employee.model.EmployeeListItem(
                e.id, e.username, e.name, e.role, m.id, m.name
            )
            FROM Employee e LEFT JOIN e.manager m
            WHERE (:managerId IS NULL OR e.manager.id = :managerId)
                AND (:role IS NULL OR e.role = :role)
                AND e.deletedAt IS NULL
            """)
    Slice<EmployeeListItem> findByManagerAndRole(
            @Param("managerId") Long managerId,
            @Param("role") Role role,
            Pageable page
    );
}
