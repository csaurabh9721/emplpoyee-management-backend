package com.devix.employemanagement.repo;

import com.devix.employemanagement.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmployeeCode(String employeeCode);
}
