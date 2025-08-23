package com.paravar.employee;

import com.paravar.user.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT new com.paravar.user.EmployeeDetails(e.id, e.username, e.password, e.role, m.id) FROM Employee e LEFT JOIN e.manager m WHERE e.username = :username")
    Optional<EmployeeDetails> findByUsername(@Param("username") String username);


    @Query("SELECT new com.paravar.employee.EmployeeDTO(e.id, e.username, e.role, m.id) FROM Employee e LEFT JOIN e.manager m")
    List<EmployeeDTO> findAllWithManagerId();


    @Query("SELECT new com.paravar.employee.EmployeeWithManagerDTO(e.id, e.username, e.role, m.id, m.username, m.role) FROM Employee e LEFT JOIN e.manager m WHERE e.id = :id")
    Optional<EmployeeWithManagerDTO> findByIdWithManager(@Param("id") Long id);

    /*
    we have disabled open-in-view, so Fetching Employee.manager will cause LazyLoadingInitialization error
      - so use DTO

    * */


    /*
    Note:-
     We can use
        @EntityGraph(attributePaths = {"id"})

      to fetch only few fields from nested objected...in our case manager

    * */

}